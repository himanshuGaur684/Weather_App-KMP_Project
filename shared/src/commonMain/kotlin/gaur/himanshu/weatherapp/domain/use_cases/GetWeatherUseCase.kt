package gaur.himanshu.weatherapp.domain.use_cases

import gaur.himanshu.weatherapp.domain.model.Weather
import gaur.himanshu.weatherapp.domain.repository.WeatherRepository

class GetWeatherUseCase(private val weatherRepository: WeatherRepository) {

    suspend operator fun invoke(lat: Double, lon: Double): Result<Weather> {
        return try {
            val response = weatherRepository.getWeather(lat, lon)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}