package gaur.himanshu.weatherapp.data.di

import gaur.himanshu.weatherapp.data.remote.ApiService
import gaur.himanshu.weatherapp.data.remote.KtorClient
import gaur.himanshu.weatherapp.data.repository.WeatherRepositoryImpl
import gaur.himanshu.weatherapp.domain.repository.WeatherRepository
import org.koin.dsl.module

val dataModule = module {
    factory { KtorClient.client }
    factory <ApiService>{  ApiService(get()) }
    factory<WeatherRepository> { WeatherRepositoryImpl(get()) }
}