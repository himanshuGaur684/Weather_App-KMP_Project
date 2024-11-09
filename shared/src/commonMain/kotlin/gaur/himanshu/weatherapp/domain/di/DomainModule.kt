package gaur.himanshu.weatherapp.domain.di

import gaur.himanshu.weatherapp.domain.use_case.GetCurrentWeatherInfoUseCase
import gaur.himanshu.weatherapp.domain.use_case.GetForecastInfoUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetCurrentWeatherInfoUseCase(get()) }
    factory { GetForecastInfoUseCase(get()) }
}