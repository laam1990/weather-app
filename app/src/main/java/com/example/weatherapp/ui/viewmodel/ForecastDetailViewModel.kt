package com.example.weatherapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.util.Resource
import com.example.weatherapp.repository.ForecastRepository
import com.example.weatherapp.ui.models.ForecastViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastDetailViewModel @Inject constructor(
    private val repository: ForecastRepository
) : ViewModel() {

    private val _forecastLiveData = MutableLiveData<Resource<ForecastViewData>>()
    val forecastLiveData: LiveData<Resource<ForecastViewData>> = _forecastLiveData

    fun getForecast(name: String) {
        viewModelScope.launch {
            repository.getForecast(name).collect {
                _forecastLiveData.value = it
            }
        }
    }
}