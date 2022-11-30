package com.example.weatherapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastFutureDayData(
    @Json(name = "maxtemp_c")
    val maxTempC: Double? = null,
    @Json(name = "mintemp_c")
    val minTempC: Double? = null,
    @Json(name = "maxwind_kph")
    val maxWindKph: Int? = null
)
