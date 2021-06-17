package com.example.subwaymvvm.data.repository.datasource

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.subwaymvvm.data.entity.StationEntity
import com.example.subwaymvvm.data.entity.StationSubwayCrossRefEntity
import com.example.subwaymvvm.data.entity.StationWithSubwayEntity
import com.example.subwaymvvm.data.entity.SubwayEntity
import kotlinx.coroutines.flow.Flow

interface StationLocalDataSource {
    // Room
    fun getStationWithSubways() : Flow<List<StationWithSubwayEntity>>

    suspend fun insertStations(station: List<StationEntity>)

    suspend fun insertSubways(subways: List<SubwayEntity>)

    suspend fun  insertCrossReferences(reference: List<StationSubwayCrossRefEntity>)

    suspend fun insertStationSubways(stationSubways: List<Pair<StationEntity, SubwayEntity>>)
}