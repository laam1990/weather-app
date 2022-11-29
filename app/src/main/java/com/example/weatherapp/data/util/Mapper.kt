package com.example.weatherapp.data.util

interface Mapper<Output, Input> {
    fun executeMapping(type: Input): Output
}
