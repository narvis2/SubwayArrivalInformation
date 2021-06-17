package com.example.subwaymvvm.data.repository.datasource

import com.example.subwaymvvm.data.model.response.RealtimeStationArrivals
import retrofit2.Response

interface StationRemoteDataSource {

    suspend fun getRealtimeStationArrivals(stationName: String) : Response<RealtimeStationArrivals>
}