package com.task.rateapp.di

import org.koin.core.module.Module

fun appModules(): List<Module> {
    return listOf(
        appModule
    )
}