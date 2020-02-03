package com.task.rateapp.viewmodels.banks.implementation

import androidx.lifecycle.Observer
import com.task.rateapp.viewmodels.base.IDisposableCommand
import com.task.rateapp.viewmodels.base.implementation.OnceExecutableCommand

class SetupBanksViewModelCommand(private val viewModel: BanksViewModel) : OnceExecutableCommand(),
    IDisposableCommand {

    private val typeChangeObserver = Observer<Int> {
        viewModel.filterCommand.execute()
    }

    override fun executeOnce() {
        viewModel.cashTypePosition.observeForever(typeChangeObserver)
        viewModel.currencyTypePosition.observeForever(typeChangeObserver)
    }

    override fun dispose() {
        viewModel.cashTypePosition.removeObserver(typeChangeObserver)
        viewModel.currencyTypePosition.removeObserver(typeChangeObserver)
    }
}
