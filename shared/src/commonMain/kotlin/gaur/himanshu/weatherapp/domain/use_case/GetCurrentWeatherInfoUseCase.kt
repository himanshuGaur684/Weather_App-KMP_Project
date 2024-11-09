package gaur.himanshu.weatherapp.domain.use_case

import gaur.himanshu.weatherapp.domain.model.Weather
import gaur.himanshu.weatherapp.domain.repository.WeatherRepository

class GetCurrentWeatherInfoUseCase (private val weatherRepository: WeatherRepository){


    suspend operator fun invoke(lat:Double,long:Double) : Result<Weather>{
        return try {
            val response = weatherRepository.getCurrentWeatherInfo(lat, long)
            Result.success(response)
        }catch (e:Exception){
            Result.failure(e)
        }
    }

}