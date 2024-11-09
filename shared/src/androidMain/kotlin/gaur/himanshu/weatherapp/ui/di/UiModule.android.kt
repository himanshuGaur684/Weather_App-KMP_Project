package gaur.himanshu.weatherapp.ui.di

import gaur.himanshu.weatherapp.ui.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

private val viewModelModule = module {
    viewModel { WeatherViewModel(get(), get()) }
}

actual fun sharedViewModelModule(): Module = viewModelModule