package com.example.subwaymvvm.data.repository.datasourceimpl

import com.example.subwaymvvm.data.model.entity.StationEntity
import com.example.subwaymvvm.data.model.entity.SubwayEntity
import com.example.subwaymvvm.data.repository.datasource.StationStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await

class StationStorageImpl(
    private val storage: StorageReference
) : StationStorage {

    override suspend fun getStationDataUpdatedTimeMillis(): Long {
        return storage.metadata.await().updatedTimeMillis
    }

    override suspend fun getStationSubways(): List<Pair<StationEntity, SubwayEntity>> {
        val downloadSizeBytes = storage.metadata.await().sizeBytes
        val byteArray = storage.getBytes(downloadSizeBytes).await()

        val returnValue = byteArray.decodeToString()
            .lines()
            .drop(1)
            .map {
                it.split(",")
            }
            .map {
                StationEntity(it[1]) to SubwayEntity(it[0].toInt())
            }

        return returnValue
    }

}