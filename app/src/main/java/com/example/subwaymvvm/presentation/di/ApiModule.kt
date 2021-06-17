package com.example.subwaymvvm.presentation.di

import com.example.subwaymvvm.BuildConfig
import com.example.subwaymvvm.data.api.StationArrivalsApiService
import com.example.subwaymvvm.data.util.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule : Module = module {

    single<StationArrivalsApiService> { get<Retrofit>().create(StationArrivalsApiService::class.java) }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(get<GsonConverterFactory>())
            .client(get<OkHttpClient>())
            .build()
    }

    single<OkHttpClient> {
        OkHttpClient.Builder().apply {
            addInterceptor(get<HttpLoggingInterceptor>())
        }.build()
    }

    single<HttpLoggingInterceptor> {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    single<GsonConverterFactory> { GsonConverterFactory.create() }
}