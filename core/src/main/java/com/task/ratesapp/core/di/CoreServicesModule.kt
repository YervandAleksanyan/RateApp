package com.task.ratesapp.core.di

import com.task.ratesapp.core.services.IRateAppApiService
import com.task.ratesapp.core.services.implementation.RateAppApiService
import org.greenrobot.eventbus.EventBus
import org.koin.dsl.module

val coreServicesModule = module {
    factory<IRateAppApiService> { RateAppApiService(get()) }
    single { EventBus() }
}