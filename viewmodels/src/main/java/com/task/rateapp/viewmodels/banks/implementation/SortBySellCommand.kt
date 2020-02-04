package com.task.rateapp.viewmodels.banks.implementation

import com.task.animalsapp.viewmodel.base.implementation.BaseCommand

class SortBySellCommand(private val viewModel: BanksViewModel) : BaseCommand() {

    override fun executeCore() {
        if (viewModel.sortBySellOrder.value == true) {
            viewModel.itemsMutable.value = viewModel.items.value?.sortedBy {
                it.sell
            }
        } else {
            viewModel.itemsMutable.value = viewModel.items.value?.sortedByDescending {
                it.sell
            }
        }
    }

}
