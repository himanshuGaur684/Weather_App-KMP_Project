package gaur.himanshu.weatherapp.data.mappers

import gaur.himanshu.weatherapp.data.model.WeatherResponse
import gaur.himanshu.weatherapp.data.model.details.WeatherDetailsDTO
import gaur.himanshu.weatherapp.domain.model.Weather
import gaur.himanshu.weatherapp.domain.model.WeatherItem

fun WeatherResponse.toDomain(): Weather {
    return Weather(
        temperature = main.temp.toFloat().minus(273f),
        icon = weather.first().icon,
        country = name
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

expect fun formatFloatUpToTwoDecimalPlaces(float: Float): String