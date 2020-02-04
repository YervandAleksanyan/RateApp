package com.task.rateapp.viewmodels.base.implementation

import com.task.animalsapp.viewmodel.base.implementation.BaseCommand


abstract class OnceExecutableCommand : BaseCommand() {
    abstract fun executeOnce()

    final override fun executeCore() {
        executeOnce()
        setCanExecute(false)
    }
}