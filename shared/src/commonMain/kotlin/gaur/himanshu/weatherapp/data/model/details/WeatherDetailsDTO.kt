package gaur.himanshu.weatherapp.data.model.details

import gaur.himanshu.weatherapp.data.model.MainDTO
import gaur.himanshu.weatherapp.data.model.WeatherDTO
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDetailsDTO(
    val dt: Int,
    val main: MainDTO,
    val weather: List<WeatherDTO>,
)