package com.example.subwaymvvm.presentation.di

import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule : Module = module {

    single { Dispatchers.IO }
    single { Dispatchers.Main }


}