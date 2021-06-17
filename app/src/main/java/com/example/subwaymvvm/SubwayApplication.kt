package com.example.subwaymvvm

import android.app.Application
import com.example.subwaymvvm.presentation.di.*
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class SubwayApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(
                if (BuildConfig.DEBUG) {
                    Level.DEBUG
                } else {
                    Level.NONE
                }
            )

            androidContext(this@SubwayApplication)

            modules(
                appModule,
                apiModule,
                firebaseModule,
                localDataSourceModule,
                remoteDataSourceModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}