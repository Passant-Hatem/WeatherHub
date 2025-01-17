package com.example.weatherhub.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.weatherhub.core.data.NonSafeComplexPreferences
import com.example.weatherhub.data.remote.WeatherRemoteDS
import com.example.weatherhub.data.repository.DefaultWeatherRepository
import com.example.weatherhub.domain.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
object WeatherDataModule {

    @Provides
    @ActivityRetainedScoped
    fun provideWeatherRemoteDS(retrofit: Retrofit): WeatherRemoteDS =
        retrofit.create(WeatherRemoteDS::class.java)
    @Provides
    @ActivityRetainedScoped
    fun provideWeatherLocalDS(@ApplicationContext appContext: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(produceFile = {
            appContext.preferencesDataStoreFile(WEATHER_DATASTORE)
        })
    }

    @Provides
    @ActivityRetainedScoped
    fun provideWeatherComplexPreference(store: DataStore<Preferences>): NonSafeComplexPreferences {
        return NonSafeComplexPreferences(store)
    }

    @Provides
    @ActivityRetainedScoped
    fun provideDefaultWeatherRepository(repository: DefaultWeatherRepository): WeatherRepository =
        repository

    private const val WEATHER_DATASTORE = "WEATHER_DATASTORE"
}