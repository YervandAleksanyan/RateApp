package com.task.rateapp.viewmodels.banks.implementation

import com.task.rateapp.viewmodels.banks.IBanksViewModel
import com.task.rateapp.viewmodels.base.IAsyncCommand
import com.task.rateapp.viewmodels.base.ICommand
import com.task.rateapp.viewmodels.base.ICommandFactory
import com.task.rateapp.viewmodels.base.implementation.BaseCommandFactory

internal class BanksViewModelCommandFactory(
    viewModel: IBanksViewModel
) : BaseCommandFactory(viewModel), ICommandFactory {
    val loadCommand: IAsyncCommand
        get() = getCommand<LoadBanksCommand>()

    val filterCommand: ICommand
        get() = getCommand<FilterBanksCommand>()

    val setupCommand: ICommand
        get() = getCommand<SetupBanksViewModelCommand>()
}
