package gaur.himanshu.weatherapp

import android.app.Application
import gaur.himanshu.weatherapp.data.di.dataModule
import gaur.himanshu.weatherapp.domain.di.domainModule
import gaur.himanshu.weatherapp.ui.di.sharedViewModelModule
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(dataModule + domainModule + sharedViewModelModule())
        }
    }

}