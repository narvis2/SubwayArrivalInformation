package com.example.subwaymvvm.domain.model

data class ArrivalInformation(
    val subway: Subway,
    val direction: String,
    val message: String,
    val destination: String,
    val updateAt: String
)
