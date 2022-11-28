package com.example.weatherapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Location(
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "region")
    val region: String? = null,
    @Json(name = "country")
    val country: String? = null,
    @Json(name = "lat")
    val lat: String? = null,
    @Json(name = "lon")
    val lon: String? = null,
    @Json(name = "tz_id")
    val timeZoneId: String? = null,
    @Json(name = "localtime_epoch")
    val localTimeEpoch: String? = null,
    @Json(name = "localtime")
    val localtime: String? = null,
)
