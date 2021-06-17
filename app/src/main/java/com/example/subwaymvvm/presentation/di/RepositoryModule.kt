package com.example.subwaymvvm.presentation.di

import com.example.subwaymvvm.data.repository.StationRepositoryImpl
import com.example.subwaymvvm.domain.repository.StationRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule: Module = module {
    single<StationRepository> { StationRepositoryImpl(get(), get(), get(), get()) }
}