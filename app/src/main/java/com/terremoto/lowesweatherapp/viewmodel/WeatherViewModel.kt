package com.terremoto.lowesweatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.terremoto.lowesweatherapp.R
import com.terremoto.lowesweatherapp.model.OpenWeatherRepository
import com.terremoto.lowesweatherapp.model.OpenWeatherResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

object WeatherViewModel : ViewModel() {
    val city = MutableLiveData<String>()
    val forecast = MutableLiveData<OpenWeatherResponse>()

    fun getWeather(cityName: String) {
        OpenWeatherRepository.getWeather(cityName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                forecast.value = it
            }, {
                Log.d("TAG_X", it.localizedMessage ?: "Error")
            })
    }

    fun getIconForWeather(icon: String?): Int {
        when (icon) {
            "01d" -> return R.drawable.ic_sun
            "02d" -> return R.drawable.ic_few_clouds
            "03d" -> return R.drawable.ic_cloud
            "04d" -> return R.drawable.ic_broken_clouds
            "09d" -> return R.drawable.ic_shower_rain
            "10d" -> return R.drawable.ic_rain
            "11d" -> return R.drawable.ic_storm
            "13d" -> return R.drawable.ic_snow
            "50d" -> return R.drawable.ic_mist
            "01n" -> return R.drawable.ic_moon
            "02n" -> return R.drawable.ic_few_clouds_night
            "03n" -> return R.drawable.ic_cloud
            "04n" -> return R.drawable.ic_broken_clouds
            "09n" -> return R.drawable.ic_shower_rain
            "10n" -> return R.drawable.ic_rain_night
            "11n" -> return R.drawable.ic_storm
            "13n" -> return R.drawable.ic_snow
            "50n" -> return R.drawable.ic_mist
            else -> return R.drawable.ic_cloud
        }
    }

}