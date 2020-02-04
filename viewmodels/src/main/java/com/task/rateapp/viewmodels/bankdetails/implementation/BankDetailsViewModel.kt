package com.task.rateapp.viewmodels.bankdetails.implementation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.rateapp.viewmodels.bankdetails.IBankDetailsViewModel
import com.task.rateapp.viewmodels.base.IAsyncCommand
import com.task.rateapp.viewmodels.base.ICommand
import com.task.rateapp.viewmodels.base.ImmutableLiveData
import com.task.rateapp.viewmodels.utils.getCommandFactory
import com.task.ratesapp.core.models.CashType
import com.task.ratesapp.core.utils.Scoped

class BankDetailsViewModel : IBankDetailsViewModel(), Scoped {

    private val commandFactory = getCommandFactory<BankDetailsViewModelCommandFactory>()

    override val bankDetails: MutableLiveData<BankDetailsArgument> = MutableLiveData()

    internal var initialItems: List<RateItemViewModel>? = emptyList()

    internal val itemsMutable: MutableLiveData<List<RateItemViewModel>> = MutableLiveData()

    override val items: LiveData<List<RateItemViewModel>> = ImmutableLiveData(itemsMutable)

    override val loadCommand: IAsyncCommand = commandFactory.loadCommand

    override val isCash: MutableLiveData<CashType> = MutableLiveData()

    override val filterCommand: ICommand = commandFactory.filterCommand

    override val setupCommand: ICommand = commandFactory.setupCommand

    override fun onCleared() {
        super.onCleared()
        closeScope()
        commandFactory.dispose()
    }
}