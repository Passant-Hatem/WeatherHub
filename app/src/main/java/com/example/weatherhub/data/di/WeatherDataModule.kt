package com.example.weatherhub.data.di

import com.example.weatherhub.data.data.DefaultWeatherRepository
import com.example.weatherhub.data.data.WeatherRemoteDS
import com.example.weatherhub.domain.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Retrofit

@Module
@InstallIn(ActivityRetainedComponent::class)
object WeatherDataModule {

    @Provides
    @ActivityRetainedScoped
    fun provideWeatherRemoteDS(retrofit: Retrofit): WeatherRemoteDS =
        retrofit.create(WeatherRemoteDS::class.java)


    @Provides
    @ActivityRetainedScoped
    fun provideDefaultWeatherRepository(repository: DefaultWeatherRepository): WeatherRepository =
        repository
}