package gaur.himanshu.weatherapp.data.model.forecast

import gaur.himanshu.weatherapp.data.model.MainDTO
import gaur.himanshu.weatherapp.data.model.WeatherDTO
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDTO(
    val dt: Int,
    val main: MainDTO,
    val weather: List<WeatherDTO>,
)