package com.example.subwaymvvm.data.repository.datasourceimpl

import com.example.subwaymvvm.data.db.StationDao
import com.example.subwaymvvm.data.model.entity.StationEntity
import com.example.subwaymvvm.data.model.entity.StationSubwayCrossRefEntity
import com.example.subwaymvvm.data.model.entity.StationWithSubwayEntity
import com.example.subwaymvvm.data.model.entity.SubwayEntity
import com.example.subwaymvvm.data.repository.datasource.StationLocalDataSource
import kotlinx.coroutines.flow.Flow

class StationLocalDataSourceImpl(
    private val stationDao: StationDao
) : StationLocalDataSource {

    override fun getStationWithSubways(): Flow<List<StationWithSubwayEntity>> {
        return stationDao.getStationWithSubways()
    }

    override suspend fun insertStations(station: List<StationEntity>) {
        return stationDao.insertStations(station)
    }

    override suspend fun insertSubways(subways: List<SubwayEntity>) {
        return stationDao.insertSubways(subways)
    }

    override suspend fun insertCrossReferences(reference: List<StationSubwayCrossRefEntity>) {
        return stationDao.insertCrossReferences(reference)
    }

    override suspend fun insertStationSubways(stationSubways: List<Pair<StationEntity, SubwayEntity>>) {
        return stationDao.insertStationSubways(stationSubways)
    }

    override suspend fun updateStation(station: StationEntity) {
        stationDao.updateStation(station)
    }
}