package com.ninjax.weather.data

import com.ninjax.weather.data.vo.Weather
import retrofit2.http.GET

interface WeatherApi {

    @GET("weather?i=London")
    suspend fun getWeather(): Weather
}
