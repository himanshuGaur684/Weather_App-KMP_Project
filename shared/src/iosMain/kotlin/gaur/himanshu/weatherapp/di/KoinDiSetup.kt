package gaur.himanshu.weatherapp.di

import gaur.himanshu.weatherapp.data.di.dataModule
import gaur.himanshu.weatherapp.domain.di.domainModule
import gaur.himanshu.weatherapp.ui.di.sharedViewModelModules
import org.koin.core.context.startKoin

fun initKoin(){
    startKoin {
        modules(dataModule + domainModule + sharedViewModelModules())
    }
}