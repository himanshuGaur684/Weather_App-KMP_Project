package gaur.himanshu.weatherapp.data.repository

import gaur.himanshu.weatherapp.data.mappers.toDomain
import gaur.himanshu.weatherapp.data.remote.ApiService
import gaur.himanshu.weatherapp.domain.model.Weather
import gaur.himanshu.weatherapp.domain.model.WeatherItem
import gaur.himanshu.weatherapp.domain.repository.WeatherRepository

class WeatherRepositoryImpl(private val apiService: ApiService) : WeatherRepository {
    override suspend fun getWeather(lat: Double, lon: Double): Weather {
        return apiService.getWeather(lat,lon).toDomain()
    }

    override suspend fun getWeatherDetails(lat: Double, lon: Double): List<WeatherItem> {
        return apiService.getWeatherDetails(lat,lon).list.toDomain()
    }
}