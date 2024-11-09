package gaur.himanshu.weatherapp.data.repository

import gaur.himanshu.weatherapp.data.mapper.toDomain
import gaur.himanshu.weatherapp.data.remote.ApiService
import gaur.himanshu.weatherapp.domain.model.ForeCast
import gaur.himanshu.weatherapp.domain.model.Weather
import gaur.himanshu.weatherapp.domain.repository.WeatherRepository

class WeatherRepositoryImpl(private val apiService: ApiService) :WeatherRepository{
    override suspend fun getCurrentWeatherInfo(lat: Double, long: Double): Weather {
        return apiService.currentWeatherInfo(lat, long).toDomain()
    }

    override suspend fun getForecastInfo(lat: Double, long: Double): List<ForeCast> {
        return apiService.forecastInfo(lat, long).toDomain()
    }
}