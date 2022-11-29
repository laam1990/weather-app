package com.example.weatherapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastLocationData(
    @Json(name = "forecastday")
    val forecastDay: List<ForecastDayData>? = null,
)
