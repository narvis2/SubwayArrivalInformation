package com.example.subwaymvvm.presentation.di

import com.example.subwaymvvm.data.repository.datasource.StationRemoteDataSource
import com.example.subwaymvvm.data.repository.datasourceimpl.StationRemoteDataSourceImpl
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import org.koin.core.module.Module
import org.koin.dsl.module

val remoteDataSourceModule : Module = module {

    single<StationRemoteDataSource> { StationRemoteDataSourceImpl(get()) }
}