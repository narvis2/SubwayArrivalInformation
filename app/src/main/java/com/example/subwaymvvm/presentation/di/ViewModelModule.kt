package com.example.subwaymvvm.presentation.di

import com.example.subwaymvvm.presentation.ui.arrival.StationArrivalsViewModel
import com.example.subwaymvvm.presentation.ui.main.MainViewModel
import com.example.subwaymvvm.presentation.ui.station.StationViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {
    single<MainViewModel> { MainViewModel() }
    single<StationViewModel> { StationViewModel(get(), get()) }
    single<StationArrivalsViewModel> { StationArrivalsViewModel() }
}