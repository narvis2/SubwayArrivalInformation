package com.example.subwaymvvm.presentation.di

import com.example.subwaymvvm.presentation.ui.arrival.StationArrivalsViewModel
import com.example.subwaymvvm.presentation.ui.main.MainViewModel
import com.example.subwaymvvm.presentation.ui.station.StationViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {
    viewModel<MainViewModel> { MainViewModel() }
    viewModel<StationViewModel> { StationViewModel(get(), get(),get()) }
    viewModel<StationArrivalsViewModel> { parametersHolder ->
        StationArrivalsViewModel(parametersHolder.get(), get(), get())
    }
}
