package com.example.weatherapp.ui.mapper

import com.example.weatherapp.data.model.ConditionData
import com.example.weatherapp.data.model.CurrentLocationData
import com.example.weatherapp.data.model.ForecastData
import com.example.weatherapp.data.model.ForecastDayData
import com.example.weatherapp.data.model.ForecastFutureDayData
import com.example.weatherapp.data.model.ForecastLocationData
import com.example.weatherapp.data.model.LocationData
import com.example.weatherapp.data.util.Mapper
import com.example.weatherapp.ui.models.ConditionViewData
import com.example.weatherapp.ui.models.CurrentLocationViewData
import com.example.weatherapp.ui.models.ForecastDayViewData
import com.example.weatherapp.ui.models.ForecastFutureDayViewData
import com.example.weatherapp.ui.models.ForecastLocationViewData
import com.example.weatherapp.ui.models.ForecastViewData
import com.example.weatherapp.ui.models.LocationViewData
import javax.inject.Inject

class ForecastMapper @Inject constructor() :
    Mapper<ForecastViewData, ForecastData> {

    override fun executeMapping(type: ForecastData): ForecastViewData {
        return ForecastViewData(
            location = parseLocationViewData(type.location),
            current = parseCurrentLocationViewData(type.current),
            forecast = parseForecastLocationViewData(type.forecast),
        )
    }

    private fun parseLocationViewData(payload: LocationData?): LocationViewData {
        return LocationViewData(
            name = payload?.name.orEmpty(),
            region = payload?.region.orEmpty(),
            country = payload?.country.orEmpty(),
            timeZoneId = payload?.timeZoneId.orEmpty(),
            localtime = payload?.localtime.orEmpty(),
            localTimeEpoch = payload?.localTimeEpoch.orEmpty()
        )
    }

    private fun parseCurrentLocationViewData(payload: CurrentLocationData?): CurrentLocationViewData {
        return CurrentLocationViewData(
            lastUpdated = payload?.lastUpdated.orEmpty(),
            lastUpdatedEpoch = payload?.lastUpdatedEpoch ?: 0,
            tempC = payload?.tempC ?: 0.0,
            windKph = payload?.windKph ?: 0.0,
            humidity = payload?.humidity ?: 0,
            cloud = payload?.cloud ?: 0,
            feelsLikeC = payload?.feelsLikeC.orEmpty(),
            conditionData = parseConditionViewData(payload?.conditionData)
        )
    }

    private fun parseForecastLocationViewData(payload: ForecastLocationData?): ForecastLocationViewData {
        return ForecastLocationViewData(
            forecastDay = parseForecastDayViewData(payload?.forecastDay)
        )
    }

    private fun parseForecastDayViewData(list: List<ForecastDayData>?): List<ForecastDayViewData>? {
        val itemList = list?.map {
            ForecastDayViewData(
                date = it.date.orEmpty(),
                dateEpoch = it.dateEpoch ?: 0,
                day = parseForecastFutureDayViewData(it.day),
                hour = parseCurrentLocationViewData(it.hour)
            )
        }
        return itemList
    }

    private fun parseForecastFutureDayViewData(payload: ForecastFutureDayData?): ForecastFutureDayViewData {
        return ForecastFutureDayViewData(
            maxTempC = payload?.maxTempC ?: 0.0,
            minTempC = payload?.minTempC ?: 0.0,
            maxWindKph = payload?.maxWindKph ?: 0.0,
            conditionData = parseConditionViewData(payload?.conditionData)
        )
    }

    private fun parseCurrentLocationViewData(list: List<CurrentLocationData>?): List<CurrentLocationViewData>? {
        val itemList = list?.map {
            CurrentLocationViewData(
                lastUpdated = it.lastUpdated.orEmpty(),
                lastUpdatedEpoch = it.lastUpdatedEpoch ?: 0,
                tempC = it.tempC ?: 0.0,
                windKph = it.windKph ?: 0.0,
                humidity = it.humidity ?: 0,
                cloud = it.cloud ?: 0,
                feelsLikeC = it.feelsLikeC.orEmpty(),
                conditionData = parseConditionViewData(it.conditionData)
            )
        }
        return itemList
    }

    private fun parseConditionViewData(payload: ConditionData?): ConditionViewData {
        return ConditionViewData(
            text = payload?.text.orEmpty(),
            icon = payload?.icon.orEmpty()
        )
    }
}