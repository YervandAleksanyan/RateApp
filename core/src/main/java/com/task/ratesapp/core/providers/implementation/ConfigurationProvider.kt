package com.task.ratesapp.core.providers.implementation

import com.task.ratesapp.core.BuildConfig
import com.task.ratesapp.core.providers.IConfigurationProvider

class ConfigurationProvider : IConfigurationProvider {

    override val apiBaseUrl: String = BuildConfig.ENDPOINT

}