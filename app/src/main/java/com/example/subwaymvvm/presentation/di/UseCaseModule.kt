package com.example.subwaymvvm.presentation.di

import com.example.subwaymvvm.domain.usecase.GetSavedStationsUseCase
import com.example.subwaymvvm.domain.usecase.RefreshStationUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val useCaseModule: Module = module {
    factory<GetSavedStationsUseCase> { GetSavedStationsUseCase(get()) }
    factory<RefreshStationUseCase> { RefreshStationUseCase(get()) }
}