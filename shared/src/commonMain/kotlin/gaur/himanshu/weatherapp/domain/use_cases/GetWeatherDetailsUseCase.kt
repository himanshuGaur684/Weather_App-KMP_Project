package gaur.himanshu.weatherapp.domain.use_cases

import gaur.himanshu.weatherapp.domain.model.WeatherItem
import gaur.himanshu.weatherapp.domain.repository.WeatherRepository

class GetWeatherDetailsUseCase (private val weatherRepository: WeatherRepository) {

    suspend operator fun invoke(lat:Double, lon:Double) :Result<List<WeatherItem>> {
        return try {
            val response = weatherRepository.getWeatherDetails(lat,lon)
            Result.success(response)
        }catch (e:Exception){
            Result.failure(e)
        }
    }

}