package com.example.weatherapp.ui.models

data class CurrentLocationViewData(
    val lastUpdatedEpoch: Int,
    val lastUpdated: String,
    val tempC: Double,
    val windKph: Double,
    val humidity: Int,
    val cloud: Int,
    val feelsLikeC: String
)
