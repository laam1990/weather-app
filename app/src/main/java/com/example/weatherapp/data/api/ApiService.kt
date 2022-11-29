package com.example.weatherapp.data.api

import com.example.weatherapp.data.api.ApiConstant.TEXT_TO_SEARCH
import com.example.weatherapp.data.api.ApiConstant.VERSION_API
import com.example.weatherapp.data.model.ForecastData
import com.example.weatherapp.data.model.SearchLocationData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(VERSION_API.plus(TEXT_TO_SEARCH))
    suspend fun getLocation(
        @Query("key") key: String,
        @Query("q") textToSearch: String
    ) : List<SearchLocationData>

}