package com.task.rateapp.viewmodels.di

import com.task.rateapp.viewmodels.bankdetails.BankDetailsViewModelBootstrapper
import com.task.rateapp.viewmodels.banks.BanksViewModelBootstrapper
import org.koin.core.module.Module

fun viewModelModules(): List<Module> {
    return listOf(
        BanksViewModelBootstrapper.module,
        BankDetailsViewModelBootstrapper.module
    )
}