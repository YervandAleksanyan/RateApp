package com.task.rateapp.viewmodels.bankdetails.implementation

import com.task.rateapp.viewmodels.bankdetails.IBankDetailsViewModel
import com.task.rateapp.viewmodels.base.IAsyncCommand
import com.task.rateapp.viewmodels.base.ICommand
import com.task.rateapp.viewmodels.base.ICommandFactory
import com.task.rateapp.viewmodels.base.implementation.BaseCommandFactory

internal class BankDetailsViewModelCommandFactory(
    viewModel: IBankDetailsViewModel
) : BaseCommandFactory(viewModel), ICommandFactory {
    val loadCommand: IAsyncCommand
        get() = getCommand<LoadBankInformationCommand>()

    val filterCommand: ICommand
        get() = getCommand<FilterRatesCommand>()

    val setupCommand: ICommand
        get() = getCommand<SetupBankDetailsViewModelCommand>()
}