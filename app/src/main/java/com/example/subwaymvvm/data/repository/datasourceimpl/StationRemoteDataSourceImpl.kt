package com.example.subwaymvvm.data.repository.datasourceimpl

import com.example.subwaymvvm.data.api.StationArrivalsApiService
import com.example.subwaymvvm.data.model.response.RealtimeStationArrivals
import com.example.subwaymvvm.data.repository.datasource.StationRemoteDataSource
import retrofit2.Response

class StationRemoteDataSourceImpl(
    private val stationArrivalsApiService: StationArrivalsApiService
) : StationRemoteDataSource {
    override suspend fun getRealtimeStationArrivals(stationName: String): Response<RealtimeStationArrivals> {
        return stationArrivalsApiService.getRealtimeStationArrivals(stationName)
    }
}