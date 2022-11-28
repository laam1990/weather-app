package com.example.weatherapp.data.api

import com.example.weatherapp.data.model.Forecast
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/v1/search.json")
    suspend fun getLocation(
        @Query("key") key: String,
        @Query("q") textToSearch: String
    ) : Forecast

}