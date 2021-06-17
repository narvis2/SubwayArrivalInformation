package com.example.subwaymvvm.presentation.ui.arrival

import androidx.lifecycle.viewModelScope
import com.example.subwaymvvm.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class StationArrivalsViewModel : BaseViewModel() {
    override fun fetchData(): Job = viewModelScope.launch {

    }
}