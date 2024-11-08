package gaur.himanshu.weatherapp.domain.di

import gaur.himanshu.weatherapp.domain.use_cases.GetWeatherDetailsUseCase
import gaur.himanshu.weatherapp.domain.use_cases.GetWeatherUseCase
import org.koin.dsl.module

val domainModule = module{
    factory { GetWeatherUseCase(get()) }
    factory { GetWeatherDetailsUseCase(get()) }
}