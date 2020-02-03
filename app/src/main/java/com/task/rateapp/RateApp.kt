package com.task.rateapp

import android.app.Application
import com.task.rateapp.di.appModules
import com.task.rateapp.viewmodels.di.viewModelModules
import com.task.ratesapp.core.di.coreModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RateApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RateApp)
            modules(
                coreModules() +
                        viewModelModules() + appModules()
            )
        }
    }
}