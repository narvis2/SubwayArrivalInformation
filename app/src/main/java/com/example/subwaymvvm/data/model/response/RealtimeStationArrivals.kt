package com.example.subwaymvvm.data.model.response


import com.google.gson.annotations.SerializedName

data class RealtimeStationArrivals(
    @SerializedName("errorMessage")
    val errorMessage: ErrorMessage? = null,
    @SerializedName("realtimeArrivalList")
    val realtimeArrivalList: List<RealtimeArrival>? = null
)