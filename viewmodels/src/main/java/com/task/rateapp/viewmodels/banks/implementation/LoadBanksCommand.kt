package com.task.rateapp.viewmodels.banks.implementation

import com.task.rateapp.viewmodels.R
import com.task.rateapp.viewmodels.base.implementation.BaseAsyncCommand
import com.task.rateapp.viewmodels.utils.getString
import com.task.ratesapp.core.services.IDialogService
import com.task.ratesapp.core.services.IRateAppApiService

class LoadBanksCommand(
    private val viewModel: BanksViewModel,
    private val apiService: IRateAppApiService,
    private val dialogService: IDialogService
) : BaseAsyncCommand() {

    override suspend fun executeCoreAsync(): Boolean {
        viewModel.initialItems = apiService.getBanksListAsync().await().banks
        viewModel.filterCommand.execute()
        return true
    }

    override suspend fun handleError(ex: Throwable) {
        super.handleError(ex)
        dialogService.displayAlert(
            getString(R.string.error),
            failureMessage.value,
            getString(R.string.ok)
        )
    }
}
