package com.task.rateapp.viewmodels.base

import androidx.lifecycle.LiveData


interface IAsyncCommand : ICommand {

    val isBusy: LiveData<Boolean>

    val isSuccessful: LiveData<Boolean>

    val failureMessage: LiveData<String?>

    suspend fun executeAsync()
    fun cancelJobs()
}