package com.example.sub_pack

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.sub_pack.WeatherService
import retrofit2.HttpException
import java.io.IOException

class WeatherViewModel : ViewModel() {
    private val apiKey = "d74e3e41538948a8fa49b1599a7840ce"
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val weatherService = retrofit.create(WeatherService::class.java)

    private val _currentTemperature = mutableStateOf(0.0)
    val currentTemperature: State<Double> = _currentTemperature
    private val _errorMessage = mutableStateOf("")
    val errorMessage: State<String> = _errorMessage

    fun fetchWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                val response = weatherService.getWeather(lat, lon, apiKey)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _currentTemperature.value = it.main.temp - 273.15  // 켈빈을 섭씨로 변환하여 저장
                    }
                } else {
                    _errorMessage.value = "Failed to fetch weather: ${response.code()}"
                }
            } catch (e: IOException) {
                _errorMessage.value = "Network error: Please check your connection."
            } catch (e: HttpException) {
                _errorMessage.value = "HTTP error: ${e.message}"
            } catch (e: Exception) {
                _errorMessage.value = "Unexpected error: ${e.localizedMessage}"
            }
        }
    }
}