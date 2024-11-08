package gaur.himanshu.weatherapp.data.remote

import gaur.himanshu.weatherapp.data.model.WeatherResponse
import gaur.himanshu.weatherapp.data.model.details.WeatherDetailsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.path


class ApiService(private val client: HttpClient) {
    // https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid=5a62a80b19dd9d3e3d6663f485720f83
    suspend fun getWeather(lat: Double, lng: Double): WeatherResponse {
        return client.get {
            url {
                host = "api.openweathermap.org"
                path("data/2.5/weather")
                parameters.append("lat", lat.toString())
                parameters.append("lon", lng.toString())
                parameters.append("appid", "5a62a80b19dd9d3e3d6663f485720f83")
            }
        }.body<WeatherResponse>()
    }

    // https://api.openweathermap.org/data/2.5/forecast?lat=44.34&lon=10.99&appid=5a62a80b19dd9d3e3d6663f485720f83
    suspend fun getWeatherDetails(lat: Double, lng: Double): WeatherDetailsResponse {
        return client.get {
            url {
                host = "api.openweathermap.org"
                path("data/2.5/forecast")
                parameters.append("lat", lat.toString())
                parameters.append("lon", lng.toString())
                parameters.append("appid", "5a62a80b19dd9d3e3d6663f485720f83")
            }
        }.body<WeatherDetailsResponse>()
    }

}