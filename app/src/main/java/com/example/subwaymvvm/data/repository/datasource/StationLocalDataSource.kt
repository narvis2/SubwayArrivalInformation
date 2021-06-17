package com.example.subwaymvvm.data.repository.datasource

import com.example.subwaymvvm.data.model.entity.StationEntity
import com.example.subwaymvvm.data.model.entity.StationSubwayCrossRefEntity
import com.example.subwaymvvm.data.model.entity.StationWithSubwayEntity
import com.example.subwaymvvm.data.model.entity.SubwayEntity
import kotlinx.coroutines.flow.Flow

interface StationLocalDataSource {
    // Room
    fun getStationWithSubways() : Flow<List<StationWithSubwayEntity>>

    suspend fun insertStations(station: List<StationEntity>)

    suspend fun insertSubways(subways: List<SubwayEntity>)

    suspend fun  insertCrossReferences(reference: List<StationSubwayCrossRefEntity>)

    suspend fun insertStationSubways(stationSubways: List<Pair<StationEntity, SubwayEntity>>)

    suspend fun updateStation(station: StationEntity)
}