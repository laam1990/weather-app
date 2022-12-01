package com.example.weatherapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConditionData(
    @Json(name = "text")
    val text: String? = null,
    @Json(name = "icon")
    val icon: String? = null
)
