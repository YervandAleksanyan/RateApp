package com.task.rateapp.viewmodels.bankdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.task.rateapp.viewmodels.bankdetails.implementation.BankDetailsArgument
import com.task.rateapp.viewmodels.bankdetails.implementation.RateItemViewModel
import com.task.rateapp.viewmodels.base.IAsyncCommand
import com.task.rateapp.viewmodels.base.ICommand
import com.task.ratesapp.core.models.CashType

abstract class IBankDetailsViewModel : ViewModel() {
    abstract val bankDetails: MutableLiveData<BankDetailsArgument>
    abstract val items: LiveData<List<RateItemViewModel>>
    abstract val loadCommand: IAsyncCommand
    abstract val isCash: MutableLiveData<CashType>
    abstract val filterCommand: ICommand
    abstract val setupCommand: ICommand
}