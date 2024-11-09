package gaur.himanshu.weatherapp.domain.repository

import gaur.himanshu.weatherapp.domain.model.ForeCast
import gaur.himanshu.weatherapp.domain.model.Weather

interface WeatherRepository {

    suspend fun getCurrentWeatherInfo(lat: Double, long: Double): Weather

    suspend fun getForecastInfo(lat: Double, long: Double): List<ForeCast>

}