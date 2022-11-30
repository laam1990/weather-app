package com.example.weatherapp.ui.models

data class ForecastDayViewData(
    val date: String,
    val dateEpoch: Int,
    val day: ForecastFutureDayViewData?,
    val hour: List<CurrentLocationViewData>?,
)
