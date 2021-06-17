package com.example.subwaymvvm.domain.usecase

import com.example.subwaymvvm.domain.model.Station
import com.example.subwaymvvm.domain.repository.StationRepository
import kotlinx.coroutines.flow.Flow

class GetSavedStationsUseCase(
    private val stationRepository: StationRepository
) {

    operator fun invoke() : Flow<List<Station>> {
        return stationRepository.stations()
    }
}