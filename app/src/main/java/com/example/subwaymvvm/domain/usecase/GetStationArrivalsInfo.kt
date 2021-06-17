package com.example.subwaymvvm.domain.usecase

import com.example.subwaymvvm.domain.model.ArrivalInformation
import com.example.subwaymvvm.domain.repository.StationRepository

class GetStationArrivalsInfo(
    private val stationRepository: StationRepository
) {

    suspend operator fun invoke(stationName: String): List<ArrivalInformation> {
        return stationRepository.getStationArrivals(stationName)
    }
}