package gaur.himanshu.weatherapp.domain.use_case

import gaur.himanshu.weatherapp.domain.model.ForeCast
import gaur.himanshu.weatherapp.domain.repository.WeatherRepository

class GetForecastInfoUseCase(private val weatherRepository: WeatherRepository) {

    suspend operator fun invoke(lat:Double, long:Double):Result<List<ForeCast>>{
        return try {
            val response = weatherRepository.getForecastInfo(lat, long)
            Result.success(response)
        }catch (e:Exception){
            Result.failure(e)
        }
    }

}