package com.terremoto.lowesweatherapp.model

import io.reactivex.rxjava3.core.Observable

object OpenWeatherRepository {

    private val weatherRetrofit = WeatherRetrofit

    fun getWeather(city: String): Observable<OpenWeatherResponse> {
        return weatherRetrofit.getWeatherResponse(city)
    }
}