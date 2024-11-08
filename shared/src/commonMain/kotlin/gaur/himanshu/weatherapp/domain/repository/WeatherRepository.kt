package gaur.himanshu.weatherapp.domain.repository

import gaur.himanshu.weatherapp.domain.model.Weather
import gaur.himanshu.weatherapp.domain.model.WeatherItem

interface WeatherRepository {

    suspend fun getWeather(lat: Double, lon: Double): Weather

    suspend fun getWeatherDetails(lat: Double, lon: Double): List<WeatherItem>

}