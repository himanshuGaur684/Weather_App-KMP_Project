package gaur.himanshu.weatherapp.data.mappers

import gaur.himanshu.weatherapp.data.model.WeatherResponse
import gaur.himanshu.weatherapp.data.model.details.WeatherDetailsDTO
import gaur.himanshu.weatherapp.domain.model.Weather
import gaur.himanshu.weatherapp.domain.model.WeatherItem
import kotlin.math.roundToInt

fun WeatherResponse.toDomain(): Weather {
    return Weather(
        weather = weather.first().main,
        description = weather.first().description,
        name = name,
        tempMax = main.temp_max.toFloat().minus(273f),
        tempMin = main.temp_min.toFloat().minus(273f),
        temperature = main.temp.toFloat().minus(273f),
        feelsLike = main.feels_like.toFloat().minus(273f),
        icon = weather.first().icon,
        country = sys.country
    )
}

fun List<WeatherDetailsDTO>.toDomain(): List<WeatherItem> {
    return map {
        WeatherItem(
            feelsLike = it.main.temp.toFloat().minus(273f),
            time = it.dt.toString(),
            icon = it.weather.first().icon
        )
    }
}

expect fun formatFloatUpToTwoDecimalPlaces(float:Float):String