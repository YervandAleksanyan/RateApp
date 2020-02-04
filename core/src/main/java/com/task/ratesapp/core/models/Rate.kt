package com.task.ratesapp.core.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rate(val currency: Currency, val exchange: Exchange) : Parcelable