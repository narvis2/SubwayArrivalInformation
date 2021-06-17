package com.example.subwaymvvm.data.mapper

import com.example.subwaymvvm.data.entity.StationEntity
import com.example.subwaymvvm.data.entity.StationSubwayCrossRefEntity
import com.example.subwaymvvm.data.entity.StationWithSubwayEntity
import com.example.subwaymvvm.data.entity.SubwayEntity
import com.example.subwaymvvm.domain.model.Station
import com.example.subwaymvvm.domain.model.Subway

fun StationWithSubwayEntity.toStation() =
    Station(
        name = station.stationName,
        isFavorited = station.isFavorited,
        connectedSubways = subways.toSubwayList()
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