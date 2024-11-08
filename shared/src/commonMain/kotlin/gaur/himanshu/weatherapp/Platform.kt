package gaur.himanshu.weatherapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform