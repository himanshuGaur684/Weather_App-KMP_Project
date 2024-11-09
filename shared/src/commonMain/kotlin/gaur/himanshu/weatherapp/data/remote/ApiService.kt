package gaur.himanshu.weatherapp.data.remote

import gaur.himanshu.weatherapp.data.model.WeatherResponse
import gaur.himanshu.weatherapp.data.model.forecast.ForecastResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.path

private const val APP_ID = "5a62a80b19dd9d3e3d6663f485720f83"

class ApiService(val client: HttpClient) {

    // https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid=5a62a80b19dd9d3e3d6663f485720f83

    suspend fun currentWeatherInfo(lat: Double, long: Double): WeatherResponse {
        return client.get {
            url {
                host = "api.openweathermap.org"
                path("data/2.5/weather")
                parameters.append("lat", lat.toString())
                parameters.append("lon", long.toString())
                parameters.append("appid", APP_ID)
            }
        }.body<WeatherResponse>()
    }

    // https://api.openweathermap.org/data/2.5/forecast?lat=44.34&lon=10.99&appid=5a62a80b19dd9d3e3d6663f485720f83
    suspend fun forecastInfo(lat: Double, long: Double): ForecastResponse {
        return client.get {
            url {
                host = "api.openweathermap.org"
                path("data/2.5/forecast")
                parameters.append("lat", lat.toString())
                parameters.append("lon", long.toString())
                parameters.append("appid", APP_ID)
            }
        }.body<ForecastResponse>()
    }

}