package com.example.weatherapp.repository

import com.example.weatherapp.data.model.ForecastData
import com.example.weatherapp.data.model.SearchLocationData
import com.example.weatherapp.ui.models.ForecastViewData
import com.example.weatherapp.ui.models.SearchLocationViewData

interface ForecastMapper {

    fun getLocation(listSearchLocationData : List<SearchLocationData>) :
        List<SearchLocationViewData>

    fun getForecast(forecastData: ForecastData) :
        ForecastViewData
}