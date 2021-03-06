package com.task.rateapp.viewmodels.banks.implementation

import androidx.lifecycle.Observer
import com.task.rateapp.viewmodels.base.IDisposableCommand
import com.task.rateapp.viewmodels.base.implementation.OnceExecutableCommand

class SetupBanksViewModelCommand(private val viewModel: BanksViewModel) : OnceExecutableCommand(),
    IDisposableCommand {

    private val typeChangeObserver = Observer<Int> {
        viewModel.filterCommand.execute()
    }

    private val sortBySellObserver = Observer<Boolean?> {
        if (it != null)
            viewModel.sortBySellCommand.execute()
    }

    private val sortByBuyObserver = Observer<Boolean?> {
        if (it != null)
            viewModel.sortByBuyCommand.execute()
    }

    override fun executeOnce() {
        viewModel.cashTypePosition.observeForever(typeChangeObserver)
        viewModel.currencyTypePosition.observeForever(typeChangeObserver)
        viewModel.sortByBuyOrder.observeForever(sortByBuyObserver)
        viewModel.sortBySellOrder.observeForever(sortBySellObserver)
    }

    override fun dispose() {
        viewModel.cashTypePosition.removeObserver(typeChangeObserver)
        viewModel.currencyTypePosition.removeObserver(typeChangeObserver)
        viewModel.sortByBuyOrder.removeObserver(sortByBuyObserver)
        viewModel.sortBySellOrder.removeObserver(sortBySellObserver)
    }
}
