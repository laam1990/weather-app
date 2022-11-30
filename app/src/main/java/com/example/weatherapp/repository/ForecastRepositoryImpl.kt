package com.example.weatherapp.repository

import com.example.weatherapp.data.api.RemoteDataSource
import com.example.weatherapp.data.util.Resource
import com.example.weatherapp.data.util.networkResource
import com.example.weatherapp.ui.models.ForecastViewData
import com.example.weatherapp.ui.models.SearchLocationViewData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val forecastMapper: ForecastMapper
) : ForecastRepository {

    override fun getLocation(textToSearch: String): Flow<Resource<List<SearchLocationViewData>>> =
        networkResource(
            networkCall = {
                remoteDataSource.getLocation(textToSearch)
            },
            mapperResponse = {
                forecastMapper.getLocation(it)
            }
        )

    override fun getForecast(name: String): Flow<Resource<ForecastViewData>> =
        networkResource(
            networkCall = {
                remoteDataSource.getForecast(name)
            },
            mapperResponse = {
                forecastMapper.getForecast(it)
            }
        )
}