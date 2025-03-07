package com.example.sub_pack

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

interface WeatherService {
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String
    ): Response<WeatherResponse>
}