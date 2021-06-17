package com.example.subwaymvvm.domain.usecase

import com.example.subwaymvvm.domain.repository.StationRepository

class RefreshStationUseCase(
    private val stationRepository: StationRepository
) {
    suspend operator fun invoke() {
        stationRepository.refreshStations()
    }
}