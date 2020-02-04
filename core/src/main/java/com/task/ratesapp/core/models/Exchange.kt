package com.task.ratesapp.core.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Exchange(val cashType: CashType, val exchangeValues: ExchangeValues) : Parcelable