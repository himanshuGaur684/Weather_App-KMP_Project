package gaur.himanshu.weatherapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    val base: String,
    val clouds: CloudsDTO,
    val cod: Int,
    val coord: CoordDTO,
    val dt: Int,
    val id: Int,
    val main: MainDTO,
    val name: String,
    val sys: SysDTO,
    val timezone: Int,
    val visibility: Int,
    val weather: List<WeatherDTO>,
)