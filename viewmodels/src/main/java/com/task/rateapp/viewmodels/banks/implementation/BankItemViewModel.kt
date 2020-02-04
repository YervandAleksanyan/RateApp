package com.task.rateapp.viewmodels.banks.implementation

data class BankItemViewModel(
    val bankKey: String,
    val name: String,
    val sell: String,
    val buy: String
) {
    var maxSell: String = ""
    var maxBuy: String = ""
}

