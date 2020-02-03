package com.task.rateapp.viewmodels.utils

import androidx.lifecycle.ViewModel
import com.task.rateapp.viewmodels.base.ICommandFactory
import com.task.ratesapp.core.utils.Scoped
import org.koin.core.parameter.parametersOf
import org.koin.ext.getFullName

internal inline fun <reified CF : ICommandFactory> ViewModel.getCommandFactory(): CF =
    if (this is Scoped) {
        scope.get {
            parametersOf(this)
        }
    } else {
        throw UnsupportedOperationException("${this::class.getFullName()} should implement ${Scoped::class.getFullName()}")
    }