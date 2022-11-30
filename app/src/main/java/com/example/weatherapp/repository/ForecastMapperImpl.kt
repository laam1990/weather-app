package com.example.weatherapp.repository

import com.example.weatherapp.data.model.ForecastData
import com.example.weatherapp.data.model.SearchLocationData
import com.example.weatherapp.ui.mapper.SearchLocationMapper
import com.example.weatherapp.ui.models.ForecastViewData
import com.example.weatherapp.ui.models.SearchLocationViewData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ForecastMapperImpl @Inject constructor(
    private val searchLocationMapper: SearchLocationMapper,
    private val forecastMapper: com.example.weatherapp.ui.mapper.ForecastMapper
) : ForecastMapper {

    override fun getLocation(listSearchLocationData: List<SearchLocationData>):
        List<SearchLocationViewData> =
        searchLocationMapper.executeMapping(listSearchLocationData)

    override fun getForecast(forecastData: ForecastData):
        ForecastViewData =
        forecastMapper.executeMapping(forecastData)

}