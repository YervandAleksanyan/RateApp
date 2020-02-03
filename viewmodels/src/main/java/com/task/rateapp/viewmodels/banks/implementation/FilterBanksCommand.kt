package com.task.rateapp.viewmodels.banks.implementation

import com.task.animalsapp.viewmodel.base.implementation.BaseCommand
import com.task.ratesapp.core.models.CashType
import com.task.ratesapp.core.models.Currency


class FilterBanksCommand(private val viewModel: BanksViewModel) : BaseCommand() {

    override fun executeCore() {
        val result = ArrayList<BankItemViewModel>()
        viewModel.initialItems.map { bank ->
            val rate =
                bank.rates.find {
                    it.currency == Currency.values()[viewModel.currencyTypePosition.value!!]
                            && it.exchange.cashType == CashType.values()[viewModel.cashTypePosition.value!!]
                }
            if (rate != null) {
                result += BankItemViewModel(
                    bank.key,
                    bank.title,
                    rate.exchange.exchangeValues.sell.toString(),
                    rate.exchange.exchangeValues.buy.toString()
                )
            }
        }
        viewModel.itemsMutable.value = result
    }

}
