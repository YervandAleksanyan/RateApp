package com.task.rateapp.viewmodels.banks.implementation

import com.task.rateapp.viewmodels.base.implementation.BaseAsyncCommand
import com.task.ratesapp.core.services.IRateAppApiService

class LoadBanksCommand(
    private val viewModel: BanksViewModel,
    private val apiService: IRateAppApiService
) : BaseAsyncCommand() {

    override suspend fun executeCoreAsync(): Boolean {
        viewModel.initialItems = apiService.getBanksListAsync().await().banks
        viewModel.filterCommand.execute()
        return true
    }

}
