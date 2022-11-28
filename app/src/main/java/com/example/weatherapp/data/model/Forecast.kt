package com.example.weatherapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Forecast(
    @Json(name = "location")
    val location: Location? = null,
    @Json(name = "current")
    val current: CurrentLocation? = null/*,
    @Json(name = "forecast")
    val forecast: ForecastLocation? = null,*/
)
