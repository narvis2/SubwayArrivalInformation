package com.example.subwaymvvm.data.repository.datasource

import com.example.subwaymvvm.data.model.entity.StationEntity
import com.example.subwaymvvm.data.model.entity.SubwayEntity

interface StationStorage {

    // 역 데이터 업데이트 시간 가져오기
    suspend fun getStationDataUpdatedTimeMillis() : Long

    // 지하철 역, 지하철(1호선, 2호선, ...) 가져오기
    suspend fun getStationSubways() : List<Pair<StationEntity, SubwayEntity>>

}