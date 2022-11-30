package com.example.weatherapp.ui.models

data class ForecastViewData(
    val location: LocationViewData?,
    val current: CurrentLocationViewData?,
    val forecast: ForecastLocationViewData?
)
