package com.example.weatherapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastDay(
    @Json(name = "date")
    val date: String? = null,
    @Json(name = "date_epoch")
    val dateEpoch: Int? = null,
    //TODO add structure
    @Json(name = "day")
    val day: Int? = null,
    @Json(name = "astro")
    val astro: Int? = null,
    @Json(name = "hour")
    val hour: List<CurrentLocation>? = null,
)
