package com.task.rateapp.viewmodels.banks.implementation

import com.task.animalsapp.viewmodel.base.implementation.BaseCommand

class SortByBuyCommand(private val viewModel: BanksViewModel) : BaseCommand() {

    override fun executeCore() {
        if (viewModel.sortByBuyOrder.value == true) {
            viewModel.itemsMutable.value = viewModel.items.value?.sortedBy {
                it.buy
            }
        } else {
            viewModel.itemsMutable.value = viewModel.items.value?.sortedByDescending {
                it.buy
            }
        }
    }

}
