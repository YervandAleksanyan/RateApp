package com.task.animalsapp.viewmodel.base.implementation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.rateapp.viewmodels.base.ICommand
import com.task.rateapp.viewmodels.base.ImmutableLiveData

abstract class BaseCommand(canExecute: Boolean = true) : ICommand {

    private val isExecutableMutable = MutableLiveData(canExecute)
    override val isExecutable: LiveData<Boolean> = ImmutableLiveData(isExecutableMutable)

    protected fun setCanExecute(canExecute: Boolean) {
        isExecutableMutable.value = canExecute
    }

    protected abstract fun executeCore()

    final override fun execute() {
        if (isExecutable.value == true) {
            executeCore()
        }
    }
}