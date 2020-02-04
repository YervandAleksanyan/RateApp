package com.task.rateapp.viewmodels.bankdetails.implementation

import com.task.animalsapp.viewmodel.base.implementation.BaseCommand

class FilterRatesCommand(private val viewModel: BankDetailsViewModel) : BaseCommand() {

    override fun executeCore() {
        if (!viewModel.initialItems.isNullOrEmpty()) {
            viewModel.itemsMutable.value = viewModel.initialItems?.filter {
                it.cashType == viewModel.isCash.value
            }
        }
    }
}
