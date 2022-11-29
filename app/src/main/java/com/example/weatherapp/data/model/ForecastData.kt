package com.example.weatherapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastData(
    @Json(name = "location")
    val location: LocationData? = null,
    @Json(name = "current")
    val current: CurrentLocationData? = null/*,
    @Json(name = "forecast")
    val forecast: ForecastLocation? = null,*/
)
