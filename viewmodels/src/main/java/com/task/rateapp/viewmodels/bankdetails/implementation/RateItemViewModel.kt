package com.task.rateapp.viewmodels.bankdetails.implementation

import com.task.ratesapp.core.models.CashType
import com.task.ratesapp.core.models.Currency

data class RateItemViewModel(
    val currency: Currency,
    val cashType: CashType,
    var sell: String,
    val buy: String
)