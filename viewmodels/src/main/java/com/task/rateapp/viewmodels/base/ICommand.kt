package com.task.rateapp.viewmodels.base

import androidx.lifecycle.LiveData

interface ICommand {

    val isExecutable: LiveData<Boolean>

    fun execute()
}