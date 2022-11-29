package com.example.weatherapp.repository

import com.example.weatherapp.data.model.SearchLocationData
import com.example.weatherapp.ui.models.SearchLocationViewData

interface ForecastMapper {

    fun getLocation(listSearchLocationData : List<SearchLocationData>) :
        List<SearchLocationViewData>
}