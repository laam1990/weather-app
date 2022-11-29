package com.example.weatherapp.di

import com.example.weatherapp.data.api.RemoteDataSource
import com.example.weatherapp.repository.ForecastMapper
import com.example.weatherapp.repository.ForecastRepository
import com.example.weatherapp.repository.ForecastRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideForecastRepositoryImpl(
        remoteDataSource: RemoteDataSource,
        forecastMapper: ForecastMapper
    ) = ForecastRepositoryImpl(
        remoteDataSource,
        forecastMapper
    ) as ForecastRepository

}