package com.example.weatherapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchLocation(
    @Json(name = "id")
    val id: Int? = null,
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
    @Json(name = "url")
    val url: String? = null
)
