package com.example.subwaymvvm.data.repository

import com.example.subwaymvvm.data.mapper.pairToStationList
import com.example.subwaymvvm.data.mapper.pairToSubwayList
import com.example.subwaymvvm.data.mapper.toCrossRefEntityList
import com.example.subwaymvvm.data.mapper.toStationList
import com.example.subwaymvvm.data.preference.PreferenceManager
import com.example.subwaymvvm.data.repository.datasource.StationLocalDataSource
import com.example.subwaymvvm.data.repository.datasource.StationStorage
import com.example.subwaymvvm.data.util.Constants.KEY_LAST_DATABASE_UPDATED_TIME_MILLIS
import com.example.subwaymvvm.domain.model.Station
import com.example.subwaymvvm.domain.repository.StationRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class StationRepositoryImpl(
    private val stationLocalDataSource: StationLocalDataSource,
    private val stationStorage: StationStorage,
    private val preferenceManager: PreferenceManager,
    private val dispatcher: CoroutineDispatcher
) : StationRepository {

    override fun stations(): Flow<List<Station>> =
        stationLocalDataSource
            .getStationWithSubways()
            .distinctUntilChanged() // 과도한 방출 막기
            .map {
                it.toStationList().sortedByDescending {
                    it.isFavorited
                }
            }
            .flowOn(dispatcher)


    override suspend fun refreshStations()  = withContext(dispatcher){
        // 파일이 파이어베이스에 업데이트되는 시점 가져오기
        val fileUpdatedTimeMillis = stationStorage.getStationDataUpdatedTimeMillis()
        // 마지막으로 db가 업데이트 되는 시점 가져오기
        val lastDatabaseUpdatedTimeMillis = preferenceManager.getLong(KEY_LAST_DATABASE_UPDATED_TIME_MILLIS)

        if (lastDatabaseUpdatedTimeMillis == null || fileUpdatedTimeMillis > lastDatabaseUpdatedTimeMillis) {
            // 파이어베이스에 저장된 파일 가져오기
            val stationSubways = stationStorage.getStationSubways()
            // Local db 업데이트트
            stationLocalDataSource.insertStationSubways(stationSubways)
            preferenceManager.setLong(KEY_LAST_DATABASE_UPDATED_TIME_MILLIS, fileUpdatedTimeMillis)
        }
    }
}