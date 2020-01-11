package com.example.myapplication.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    companion object {
        const val API_URL = "https://api.openweathermap.org/data/2.5/"
        const val API_KEY = "1ce570afd0265e8b283fd346334cafe5"
    }

    @GET("weather?APPID=$API_KEY&units=metric")
    fun getCurrentWeatherForCity(@Query("q") cityAndCode: String): Call<Weather>
}