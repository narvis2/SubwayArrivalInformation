package com.example.subwaymvvm.presentation.di

import androidx.room.Room
import com.example.subwaymvvm.data.db.StationDao
import com.example.subwaymvvm.data.db.StationDatabase
import com.example.subwaymvvm.data.preference.PreferenceManager
import com.example.subwaymvvm.data.repository.datasource.StationLocalDataSource
import com.example.subwaymvvm.data.repository.datasource.StationStorage
import com.example.subwaymvvm.data.repository.datasourceimpl.StationLocalDataSourceImpl
import com.example.subwaymvvm.data.repository.datasourceimpl.StationStorageImpl
import com.example.subwaymvvm.data.util.Constants.DATABASE_NAME
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val localDataSourceModule : Module = module {
    single<StationLocalDataSource> { StationLocalDataSourceImpl(get()) }
    single<StationStorage> { StationStorageImpl(get()) }
    single<PreferenceManager> { PreferenceManager(androidContext()) }

    single<StationDao> { get<StationDatabase>().stationDao() }

    single<StationDatabase> {
        Room.databaseBuilder(
            androidApplication(),
            StationDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}