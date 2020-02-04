package com.task.rateapp.viewmodels.bankdetails.implementation

import android.os.Parcelable
import com.task.ratesapp.core.models.Rate
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BankDetailsArgument(
    val name: String,
    val bankKey: String,
    val rates: List<Rate>
) : Parcelable