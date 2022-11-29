package com.example.weatherapp.data.api

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.model.SearchLocationData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSourceImpl @Inject constructor(private val apiService: ApiService) :
    RemoteDataSource {

    override suspend fun getLocation(textToSearch: String): List<SearchLocationData> =
        apiService.getLocation(BuildConfig.WHEATER_API_KEY, textToSearch)
}