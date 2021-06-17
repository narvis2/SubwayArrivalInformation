package com.example.subwaymvvm.data.api

import com.example.subwaymvvm.BuildConfig
import com.example.subwaymvvm.data.model.response.RealtimeStationArrivals
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StationArrivalsApiService {

    @GET("api/subway/${BuildConfig.API_KEY}/json/realtimeStationArrival/0/100/{stationName}")
    suspend fun getRealtimeStationArrivals(
        @Path("stationName") stationName: String
    ) : Response<RealtimeStationArrivals>
}