package com.task.rateapp.viewmodels.bankdetails.implementation

import androidx.lifecycle.Observer
import com.task.rateapp.viewmodels.base.IDisposableCommand
import com.task.rateapp.viewmodels.base.implementation.OnceExecutableCommand
import com.task.ratesapp.core.models.CashType

class SetupBankDetailsViewModelCommand(private val viewModel: BankDetailsViewModel) :
    OnceExecutableCommand(), IDisposableCommand {

    private val isCashObserver = Observer<CashType> {
        viewModel.filterCommand.execute()
    }

    override fun executeOnce() {
        viewModel.isCash.observeForever(isCashObserver)
    }

    override fun dispose() {
        viewModel.isCash.removeObserver(isCashObserver)
    }

}
