package com.task.rateapp.di

import com.task.rateapp.services.DialogService
import com.task.ratesapp.core.services.IDialogService
import org.koin.dsl.module

val appModule = module {
    single<IDialogService> { DialogService() }
}
