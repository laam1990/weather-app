package com.example.weatherapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentLocationData(
    @Json(name = "last_updated_epoch")
    val lastUpdatedEpoch: Int? = null,
    @Json(name = "last_updated")
    val lastUpdated: String? = null,
    @Json(name = "temp_c")
    val tempC: Double? = null,
    @Json(name = "wind_kph")
    val windKph: Double? = null,
    @Json(name = "humidity")
    val humidity: Int? = null,
    @Json(name = "cloud")
    val cloud: Int? = null,
    @Json(name = "feelslike_c")
    val feelsLikeC: String? = null
)


