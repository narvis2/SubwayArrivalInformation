package com.example.subwaymvvm.presentation.ui.arrival

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.subwaymvvm.domain.model.ArrivalInformation
import com.example.subwaymvvm.domain.model.Station
import com.example.subwaymvvm.domain.usecase.GetStationArrivalsInfo
import com.example.subwaymvvm.domain.usecase.UpdateStationUseCase
import com.example.subwaymvvm.presentation.base.BaseViewModel
import com.example.subwaymvvm.presentation.utils.Event
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class StationArrivalsViewModel(
    private val station: Station,
    private val getStationArrivalsInfo: GetStationArrivalsInfo,
    private val updateStationUseCase: UpdateStationUseCase
) : BaseViewModel() {

    private val _arrivalsInfo = MutableLiveData<List<ArrivalInformation>?>()
    val arrivalsInfo: LiveData<List<ArrivalInformation>?>
        get() = _arrivalsInfo

    private val _error = MutableLiveData<Event<String>>()
    val error : LiveData<Event<String>>
        get() = _error

    override fun fetchData(): Job = viewModelScope.launch {
        fetchStationArrivals(station.name)
        toggleStationFavorite(station)
    }

    fun fetchStationArrivals(stationName: String) = viewModelScope.launch {
        try {
            showProgress()
            _arrivalsInfo.postValue(getStationArrivalsInfo(stationName))
        }catch (e:Exception) {
            e.printStackTrace()
            _error.postValue(Event(e.message ?:"알 수 없는 문제가 발생했어요 \uD83D\uDE22"))
        } finally {
            hideProgress()
        }
    }

    fun toggleStationFavorite(station: Station) = viewModelScope.launch {
        updateStationUseCase(station.copy(isFavorited = !station.isFavorited))
    }
}