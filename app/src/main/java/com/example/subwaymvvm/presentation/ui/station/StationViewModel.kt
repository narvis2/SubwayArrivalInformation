package com.example.subwaymvvm.presentation.ui.station

import androidx.lifecycle.*
import com.example.subwaymvvm.domain.model.Station
import com.example.subwaymvvm.domain.usecase.GetSavedStationsUseCase
import com.example.subwaymvvm.domain.usecase.RefreshStationUseCase
import com.example.subwaymvvm.domain.usecase.UpdateStationUseCase
import com.example.subwaymvvm.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.core.component.getScopeName

class StationViewModel(
    private val getSavedStationsUseCase: GetSavedStationsUseCase,
    private val refreshStationUseCase: RefreshStationUseCase,
    private val updateStationUseCase: UpdateStationUseCase
) : BaseViewModel() {

    private val queryString : MutableStateFlow<String> = MutableStateFlow("")
    private val _stations: MutableLiveData<List<Station>> = MutableLiveData(emptyList())
    val stations: LiveData<List<Station>>
        get() = _stations

    override fun fetchData(): Job = viewModelScope.launch {
        getSavedStation()
        refreshStationUseCase()
    }

    fun filterStation(query: String) = viewModelScope.launch {
        queryString.emit(query)
    }

    private fun getSavedStation() {
        getSavedStationsUseCase()
            .combine(queryString) { stations: List<Station>, query: String ->
                if (query.isBlank()) {
                    stations
                } else {
                    stations.filter {
                        it.name.contains(query)
                    }
                }
            }
            .onStart { showProgress() }
            .onEach {
                if (it.isNotEmpty()) {
                    showProgress()
                }
                _stations.postValue(it)
                hideProgress()
            }
            .catch {
                it.printStackTrace()
                hideProgress()
            }
            .launchIn(viewModelScope)
    }

    fun toggleStationFavorite(station: Station) = viewModelScope.launch {
        updateStationUseCase(station.copy(isFavorited = !station.isFavorited))
    }
}