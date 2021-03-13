package com.terremoto.lowesweatherapp.model

data class OpenWeatherResponse(
    val city: City? = City(),
    val cnt: Int? = 0,
    val cod: String? = "",
    val list: List<WeatherData>? = listOf(),
    val message: Int? = 0
) {
    data class City(
        val coord: Coord? = Coord(),
        val country: String? = "",
        val id: Int? = 0,
        val name: String? = "",
        val population: Int? = 0,
        val sunrise: Int? = 0,
        val sunset: Int? = 0,
        val timezone: Int? = 0
    ) {
        data class Coord(
                val lat: Double? = 0.0,
                val lon: Double? = 0.0
        )
    }

    data class WeatherData(
        val clouds: Clouds? = Clouds(),
        val dt: Int? = 0,
        val dt_txt: String? = "",
        val main: Main? = Main(),
        val pop: Double? = 0.0,
        val rain: Rain? = Rain(),
        val snow: Snow? = Snow(),
        val sys: Sys? = Sys(),
        val visibility: Int? = 0,
        val weather: List<Weather?>? = listOf(),
        val wind: Wind? = Wind()
    ) {
        data class Clouds(
                val all: Int? = 0
        )

        data class Main(
                val feels_like: Double? = 0.0,
                val grnd_level: Int? = 0,
                val humidity: Int? = 0,
                val pressure: Int? = 0,
                val sea_level: Int? = 0,
                val temp: Double? = 0.0,
                val temp_kf: Double? = 0.0,
                val temp_max: Double? = 0.0,
                val temp_min: Double? = 0.0
        )

        data class Rain(
                val `3h`: Double? = 0.0
        )

        data class Snow(
                val `3h`: Double? = 0.0
        )

        data class Sys(
                val pod: String? = ""
        )

        data class Weather(
                val description: String? = "",
                val icon: String? = "",
                val id: Int? = 0,
                val main: String? = ""
        )

        data class Wind(
                val deg: Int? = 0,
                val speed: Double? = 0.0
        )
    }
}