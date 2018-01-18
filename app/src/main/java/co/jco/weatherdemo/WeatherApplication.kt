package co.jco.weatherdemo

import co.jco.weatherdemo.di.AppModule
import co.jco.weatherdemo.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class WeatherApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out WeatherApplication> {
        val appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        appComponent.inject(this)
        return appComponent
    }
}