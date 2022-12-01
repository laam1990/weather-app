package com.example.weatherapp.data.util

sealed class Resource<T>(val data: T? = null) {
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(
        val errorTypeData: ErrorTypeData,
        data: T? = null
    ) : Resource<T>(data)
}
