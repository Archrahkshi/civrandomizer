package com.example.myapplication.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext

class WeatherRepository(
    override val coroutineContext: CoroutineContext = Dispatchers.IO
) : CoroutineScope {
    private var api = Retrofit.Builder()
        .baseUrl(WeatherApi.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherApi::class.java)

    fun getCurrentWeatherForCity(city: String, code: String) = async {
        api.getCurrentWeatherForCity("$city,$code").execute().body()
    }
}