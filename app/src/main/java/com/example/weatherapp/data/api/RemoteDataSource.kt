package com.example.weatherapp.data.api

import com.example.weatherapp.data.model.ForecastData
import com.example.weatherapp.data.model.SearchLocationData
import retrofit2.http.Query

interface RemoteDataSource {

    suspend fun getLocation(
        @Query("q") textToSearch: String
    ) : List<SearchLocationData>

    suspend fun getForecast(
        @Query("q") name: String
    ) : ForecastData
}