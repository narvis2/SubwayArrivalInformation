package com.example.subwaymvvm.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.subwaymvvm.data.model.entity.StationEntity
import com.example.subwaymvvm.data.model.entity.StationSubwayCrossRefEntity
import com.example.subwaymvvm.data.model.entity.SubwayEntity

@Database(
    entities = [StationEntity::class, SubwayEntity::class, StationSubwayCrossRefEntity::class],
    version = 1,
    exportSchema = false
)
abstract class StationDatabase : RoomDatabase() {

    abstract fun stationDao() : StationDao

}