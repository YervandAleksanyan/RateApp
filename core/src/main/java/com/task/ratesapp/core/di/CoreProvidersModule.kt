package com.task.ratesapp.core.di

import com.task.ratesapp.core.providers.IConfigurationProvider
import com.task.ratesapp.core.providers.implementation.ConfigurationProvider
import org.koin.dsl.module

val coreProvidersModule = module {
    factory<IConfigurationProvider> { ConfigurationProvider() }
}