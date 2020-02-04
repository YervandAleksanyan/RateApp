package com.task.rateapp.viewmodels.banks.implementation

import com.task.ratesapp.core.models.Rate

data class BankItemViewModel(
    val bankKey: String,
    val name: String,
    val sell: String,
    val buy: String,
    val rates: List<Rate>
) {
    var maxSell: String = ""
    var maxBuy: String = ""
}

