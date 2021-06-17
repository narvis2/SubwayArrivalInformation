package com.example.subwaymvvm.domain.repository

import com.example.subwaymvvm.domain.model.ArrivalInformation
import com.example.subwaymvvm.domain.model.Station
import kotlinx.coroutines.flow.Flow


interface StationRepository {
    suspend fun getStationArrivals(stationName: String) : List<ArrivalInformation>

    fun stations(): Flow<List<Station>>

    suspend fun refreshStations()

    suspend fun updateStation(station: Station)
}