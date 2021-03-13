package com.terremoto.lowesweatherapp.model

import com.terremoto.lowesweatherapp.util.Constants.Companion.BASE_URL
import io.reactivex.rxjava3.core.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object WeatherRetrofit {
    private var openWeatherMapAPI: OpenWeatherMapAPI
    private val client = OkHttpClient.Builder().build()

    init {
        openWeatherMapAPI = createWeatherAPI(createRetrofit())
    }

    private fun createWeatherAPI(retrofit: Retrofit): OpenWeatherMapAPI {
        return retrofit.create(OpenWeatherMapAPI::class.java)
    }

    private fun createRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(client)
        .build()

    fun getWeatherResponse(cityName: String): Observable<OpenWeatherResponse> {
        return openWeatherMapAPI.getOpenWeatherResponse(cityName)
    }

}