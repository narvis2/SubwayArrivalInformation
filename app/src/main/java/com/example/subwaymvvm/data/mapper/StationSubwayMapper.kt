package com.example.subwaymvvm.data.mapper

import com.example.subwaymvvm.data.model.entity.StationEntity
import com.example.subwaymvvm.data.model.entity.StationSubwayCrossRefEntity
import com.example.subwaymvvm.data.model.entity.StationWithSubwayEntity
import com.example.subwaymvvm.data.model.entity.SubwayEntity
import com.example.subwaymvvm.domain.model.Station
import com.example.subwaymvvm.domain.model.Subway

fun StationWithSubwayEntity.toStation() =
    Station(
        name = station.stationName,
        isFavorited = station.isFavorited,
        connectedSubways = subways.toSubwayList()
    )
fun Station.toStationEntity() =
    StationEntity(
        stationName = name,
        isFavorited = isFavorited
    )

fun List<SubwayEntity>.toSubwayList() : List<Subway> =
    map {
        Subway.findById(it.subwayId)
    }

fun List<StationWithSubwayEntity>.toStationList() : List<Station> =
    map {
        it.toStation()
    }

fun List<Pair<StationEntity, SubwayEntity>>.toCrossRefEntityList() : List<StationSubwayCrossRefEntity> =
    map { (station, subway) ->
        StationSubwayCrossRefEntity(
            stationName = station.stationName,
            subwayId = subway.subwayId
        )
    }

fun List<Pair<StationEntity, SubwayEntity>>.pairToStationList() : List<StationEntity> =
    map {
        it.first
    }

fun List<Pair<StationEntity, SubwayEntity>>.pairToSubwayList() : List<SubwayEntity> =
    map {
        it.second
    }