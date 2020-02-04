package com.task.rateapp.viewmodels.banks.implementation

import com.task.animalsapp.viewmodel.base.implementation.BaseCommand
import com.task.ratesapp.core.models.CashType
import com.task.ratesapp.core.models.Currency


class FilterBanksCommand(private val viewModel: BanksViewModel) : BaseCommand() {

    override fun executeCore() {
        val result = ArrayList<BankItemViewModel>()
        if (!viewModel.initialItems.isNullOrEmpty()) {
            viewModel.initialItems.map { bank ->
                val rate =
                    bank.rates.find {
                        it.currency == Currency.values()[viewModel.currencyTypePosition.value!!]
                                && it.exchange.cashType == CashType.values()[viewModel.cashTypePosition.value!!]
                    }
                if (rate != null) {
                    result += BankItemViewModel(
                        bankKey = bank.key,
                        name = bank.title,
                        sell = rate.exchange.exchangeValues.sell.toString(),
                        buy = rate.exchange.exchangeValues.buy.toString(),
                        rates = bank.rates
                    )
                }
            }
            result.maxBy { it.sell }?.let {
                it.maxSell = it.sell
            }

            result.maxBy { it.buy }?.let {
                it.maxBuy = it.buy
            }

            viewModel.itemsMutable.value = result
        }
    }

}
