package com.example.subwaymvvm.presentation.di

import com.example.subwaymvvm.data.util.Constants.STATION_DATA_FILE_NAME
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import org.koin.core.module.Module
import org.koin.dsl.module

val firebaseModule: Module = module {
    single<StorageReference> { Firebase.storage.reference.child(STATION_DATA_FILE_NAME) }
}