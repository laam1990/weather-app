package com.example.weatherapp.repository

import com.example.weatherapp.data.util.Resource
import com.example.weatherapp.ui.models.ForecastViewData
import com.example.weatherapp.ui.models.SearchLocationViewData
import kotlinx.coroutines.flow.Flow

interface ForecastRepository {

    fun getLocation(
        textToSearch: String
    ) : Flow<Resource<List<SearchLocationViewData>>>

    fun getForecast(
        name: String
    ) : Flow<Resource<ForecastViewData>>
}