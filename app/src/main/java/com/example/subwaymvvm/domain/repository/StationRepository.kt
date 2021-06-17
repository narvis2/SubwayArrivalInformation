package com.example.subwaymvvm.domain.repository

import com.example.subwaymvvm.domain.model.Station
import kotlinx.coroutines.flow.Flow


interface StationRepository {

    fun stations(): Flow<List<Station>>

    suspend fun refreshStations()
}