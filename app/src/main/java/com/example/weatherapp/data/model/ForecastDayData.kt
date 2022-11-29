package com.example.weatherapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastDayData(
    @Json(name = "date")
    val date: String? = null,
    @Json(name = "date_epoch")
    val dateEpoch: Int? = null,
    //TODO add structure
    @Json(name = "day")
    val day: ForecastFutureDayData? = null,
    @Json(name = "hour")
    val hour: List<CurrentLocationData>? = null,
)
