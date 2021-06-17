package com.example.subwaymvvm.data.mapper

import com.example.subwaymvvm.data.model.response.RealtimeArrival
import com.example.subwaymvvm.domain.model.ArrivalInformation
import com.example.subwaymvvm.domain.model.Subway
import java.text.SimpleDateFormat
import java.util.*

private const val INVALID_FIELD = "-"

private val apiDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.'0'", Locale.KOREA)
private val dataFormat = SimpleDateFormat("HH:mm:ss", Locale.KOREA)

fun RealtimeArrival.toArrivalInformation() =
    ArrivalInformation(
        subway = Subway.findById(subwayId),
        /**
         * 도착지 방면
         * ex) 성수행-구로디지털단지방면
         * - 를 기준으로 뒷부분 구로디질단지방면 쪽만 사용하고 공백을 없앤다.
         */
        direction = trainLineNm?.split("-")
            ?.get(1)
            ?.trim()
            ?: INVALID_FIELD,
        /**
         * ex) [3]번째 전역 (도곡) or 6분 25초 후 (삼전)
         * 현재 역 이름과 동일하면 "당역"으로 변경
         * [] 를 ""(비어있는 문자열)로 변경
         */
        message = arvlMsg2
            ?.replace(statnNm.toString(), "당역")
            ?.replace("[\\[\\]]".toRegex(), "")
            ?: INVALID_FIELD,
        // 종착역
        destination = bstatnNm ?: INVALID_FIELD,
        /**
         * 열차 도착정보를 생성한 시각
         * "2021-05-23 22:57:57.0" String 타입으로 들어오면 Date 로 만든다음
         * 내가 보고 싶은 "HH:mm:ss" String 타입 으로 다시 변경
         */
        updateAt = recptnDt
            ?.let {
                apiDateFormat.parse(it)
            }
            ?.let {
                dataFormat.format(it)
            }
            ?: INVALID_FIELD
    )

fun List<RealtimeArrival>.toArrivalInformation() : List<ArrivalInformation> =
    map {
        it.toArrivalInformation()
    }