package com.example.subwaymvvm.data.db

import androidx.room.*
import com.example.subwaymvvm.data.entity.StationEntity
import com.example.subwaymvvm.data.entity.StationSubwayCrossRefEntity
import com.example.subwaymvvm.data.entity.StationWithSubwayEntity
import com.example.subwaymvvm.data.entity.SubwayEntity
import com.example.subwaymvvm.data.mapper.pairToStationList
import com.example.subwaymvvm.data.mapper.pairToSubwayList
import com.example.subwaymvvm.data.mapper.toCrossRefEntityList
import kotlinx.coroutines.flow.Flow

@Dao
interface StationDao {

    @Transaction
    @Query("SELECT * FROM StationEntity")
    fun getStationWithSubways() : Flow<List<StationWithSubwayEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStations(station: List<StationEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubways(subways: List<SubwayEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertCrossReferences(reference: List<StationSubwayCrossRefEntity>)

    @Transaction
    suspend fun insertStationSubways(stationSubways: List<Pair<StationEntity, SubwayEntity>>) {
        insertStations(stationSubways.pairToStationList())
        insertSubways(stationSubways.pairToSubwayList())
        insertCrossReferences(stationSubways.toCrossRefEntityList())
    }
}