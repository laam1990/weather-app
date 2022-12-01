package com.example.weatherapp.data.util

import com.example.weatherapp.data.model.ErrorBodyData

sealed class ErrorTypeData(val errorBodyData: ErrorBodyData? = null) {
    class ControlledError(errorBodyData: ErrorBodyData? = null) : ErrorTypeData(errorBodyData)
    class ConnectionError(errorBodyData: ErrorBodyData? = null) : ErrorTypeData(errorBodyData)
}