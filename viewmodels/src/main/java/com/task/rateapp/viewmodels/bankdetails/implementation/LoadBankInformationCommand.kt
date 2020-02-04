package com.task.rateapp.viewmodels.bankdetails.implementation

import com.task.rateapp.viewmodels.base.implementation.BaseAsyncCommand

class LoadBankInformationCommand(
    private val viewModel: BankDetailsViewModel
) : BaseAsyncCommand() {

    override suspend fun executeCoreAsync(): Boolean {
        viewModel.initialItems = viewModel.bankDetails.value?.rates?.map {
            RateItemViewModel(
                it.currency,
                it.exchange.cashType,
                it.exchange.exchangeValues.sell.toString(),
                it.exchange.exchangeValues.buy.toString()
            )
        }
        viewModel.filterCommand.execute()
        return true
    }

}
