package com.task.ratesapp.core.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ExchangeValues(
    val sell: Double,
    val buy: Double
) : Parcelable