package com.example.subwaymvvm.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StationEntity(
    @PrimaryKey
    val stationName: String,
    val isFavorited: Boolean = false
)
