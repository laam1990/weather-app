package com.example.weatherapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.util.Resource
import com.example.weatherapp.repository.ForecastRepository
import com.example.weatherapp.ui.models.SearchLocationViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val repository: ForecastRepository
) : ViewModel() {

    private val _locationsLiveData = MutableLiveData<Resource<List<SearchLocationViewData>>>()
    val locationLiveData: LiveData<Resource<List<SearchLocationViewData>>> = _locationsLiveData

    fun getLocations(textToSearch: String) {
        viewModelScope.launch {
            repository.getLocation(textToSearch).collect {
                _locationsLiveData.value = it
            }
        }
    }
}