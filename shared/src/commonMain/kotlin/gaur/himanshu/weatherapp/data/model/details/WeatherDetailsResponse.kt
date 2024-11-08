package gaur.himanshu.weatherapp.data.model.details

import kotlinx.serialization.Serializable

@Serializable
data class WeatherDetailsResponse(
    val list: List<WeatherDetailsDTO>,
)