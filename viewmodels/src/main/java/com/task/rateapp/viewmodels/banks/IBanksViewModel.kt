package com.task.rateapp.viewmodels.banks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.task.rateapp.viewmodels.banks.implementation.BankItemViewModel
import com.task.rateapp.viewmodels.base.IAsyncCommand
import com.task.rateapp.viewmodels.base.ICommand

abstract class IBanksViewModel : ViewModel() {
    abstract val items: LiveData<List<BankItemViewModel>>
    abstract val cashTypePosition: MutableLiveData<Int>
    abstract val currencyTypePosition: MutableLiveData<Int>
    abstract val loadCommand: IAsyncCommand
    abstract val filterCommand: ICommand
    abstract val setupCommand: ICommand
    abstract val sortBySellCommand: ICommand
    abstract val sortByBuyCommand: ICommand
    abstract val sortBySellOrder: MutableLiveData<Boolean>
    abstract val sortByBuyOrder: MutableLiveData<Boolean>
}