package com.task.rateapp

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.task.rateapp.di.appModules
import com.task.rateapp.screens.StarterActivity
import com.task.rateapp.viewmodels.di.viewModelModules
import com.task.ratesapp.core.di.coreModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RateApp : Application() {

    companion object {
        var instance: RateApp? = null
    }

    var currentActivity: StarterActivity? = null

    override fun onCreate() {
        super.onCreate()
        registerLifecycleCallback()
        instance = this
        startKoin {
            androidContext(this@RateApp)
            modules(
                coreModules() +
                        viewModelModules() + appModules()
            )
        }
    }


    private fun registerLifecycleCallback() {
        this.registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {

            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                if (activity is StarterActivity) {
                    currentActivity = activity
                }
            }

            override fun onActivityStarted(activity: Activity?) {
                if (activity is StarterActivity) {
                    currentActivity = activity
                }
            }

            override fun onActivityResumed(activity: Activity?) {
                if (activity is StarterActivity) {
                    currentActivity = activity
                }
            }

            override fun onActivityPaused(activity: Activity?) {

            }

            override fun onActivityStopped(activity: Activity?) {
                currentActivity = null
            }

            override fun onActivityDestroyed(activity: Activity?) {

            }

            override fun onActivitySaveInstanceState(
                activity: Activity?,
                savedInstanceState: Bundle?
            ) {

            }

        })
    }

}