package com.example.weatherapp.ui.mapper

import com.example.weatherapp.data.model.SearchLocationData
import com.example.weatherapp.data.util.Mapper
import com.example.weatherapp.ui.models.SearchLocationViewData
import javax.inject.Inject

class SearchLocationMapper @Inject constructor() :
    Mapper<List<SearchLocationViewData>, List<SearchLocationData>> {

    override fun executeMapping(type: List<SearchLocationData>): List<SearchLocationViewData> {
        val itemList = type.map {
            SearchLocationViewData(
                id = it.id ?: 0,
                name = it.name.orEmpty(),
                region = it.region.orEmpty(),
                country = it.country.orEmpty(),
                lat = it.lat ?: 0.0,
                lon = it.lon ?: 0.0,
                url = it.url.orEmpty()
            )
        }
        return itemList
    }
}