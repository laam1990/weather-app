package com.example.weatherapp.repository

import com.example.weatherapp.data.model.SearchLocationData
import com.example.weatherapp.ui.mapper.SearchLocationMapper
import com.example.weatherapp.ui.models.SearchLocationViewData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ForecastMapperImpl @Inject constructor(
    val searchLocationMapper: SearchLocationMapper
) : ForecastMapper {

    override fun getLocation(listSearchLocationData: List<SearchLocationData>):
        List<SearchLocationViewData> =
        searchLocationMapper.executeMapping(listSearchLocationData)
}