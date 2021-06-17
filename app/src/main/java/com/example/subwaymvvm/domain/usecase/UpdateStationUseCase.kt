package com.example.subwaymvvm.domain.usecase

import com.example.subwaymvvm.domain.model.Station
import com.example.subwaymvvm.domain.repository.StationRepository

class UpdateStationUseCase(
    private val stationRepository: StationRepository
) {

    suspend operator fun invoke(station:Station) {
        return stationRepository.updateStation(station)
    }
}