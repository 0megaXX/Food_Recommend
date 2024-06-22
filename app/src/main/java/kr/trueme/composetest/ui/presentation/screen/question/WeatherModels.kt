package com.example.sub_pack

data class WeatherResponse(
    val main: Main,
    val weather: List<Weather>,
    val name: String
)

data class Weather(
    val description: String
)

data class Main(
    val temp: Double
)