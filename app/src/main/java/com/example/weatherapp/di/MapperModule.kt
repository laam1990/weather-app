package com.example.weatherapp.di

import com.example.weatherapp.repository.ForecastMapper
import com.example.weatherapp.repository.ForecastMapperImpl
import com.example.weatherapp.ui.mapper.SearchLocationMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    @Singleton
    fun provideForecastMapperImpl(
        searchLocationMapper: SearchLocationMapper
    ) = ForecastMapperImpl(
        searchLocationMapper
    ) as ForecastMapper

}