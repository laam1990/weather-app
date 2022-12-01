package com.example.weatherapp.ui.models

import com.example.weatherapp.data.model.ConditionData

data class ForecastFutureDayViewData(
    val maxTempC: Double,
    val minTempC: Double,
    val maxWindKph: Double,
    val conditionData: ConditionViewData
)
