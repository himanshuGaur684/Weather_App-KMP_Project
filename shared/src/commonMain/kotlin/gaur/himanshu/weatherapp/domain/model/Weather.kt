package gaur.himanshu.weatherapp.domain.model

data class Weather(
    val weather: String,
    val description: String,
    val temperature: Float,
    val feelsLike: Float,
    val tempMax: Float,
    val tempMin: Float,
    val name: String,
    val icon:String,
    val country:String
)
