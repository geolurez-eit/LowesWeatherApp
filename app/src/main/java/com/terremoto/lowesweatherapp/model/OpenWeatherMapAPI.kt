package com.terremoto.lowesweatherapp.model

import com.terremoto.lowesweatherapp.util.Constants
import com.terremoto.lowesweatherapp.util.Constants.Companion.URL_PATH
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface OpenWeatherMapAPI {
    @GET(URL_PATH)
    fun getOpenWeatherResponse(
        @Query("q") q: String,
        @Query("appid") appid: String = Constants.API_KEY
    ): Observable<OpenWeatherResponse>

}