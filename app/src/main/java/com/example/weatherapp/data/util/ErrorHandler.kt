package com.example.weatherapp.data.util

interface ErrorHandler {
    suspend fun getError(throwable: Throwable): ErrorTypeData
}