package com.task.rateapp.viewmodels.banks.implementation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.rateapp.viewmodels.banks.IBanksViewModel
import com.task.rateapp.viewmodels.base.IAsyncCommand
import com.task.rateapp.viewmodels.base.ICommand
import com.task.rateapp.viewmodels.base.ImmutableLiveData
import com.task.rateapp.viewmodels.utils.getCommandFactory
import com.task.ratesapp.core.models.Bank
import com.task.ratesapp.core.utils.Scoped

class BanksViewModel : IBanksViewModel(), Scoped {

    private val commandFactory = getCommandFactory<BanksViewModelCommandFactory>()

    internal val itemsMutable = MutableLiveData<List<BankItemViewModel>>()

    internal var initialItems = emptyList<Bank>()

    override val items: LiveData<List<BankItemViewModel>> = ImmutableLiveData(itemsMutable)

    override val cashTypePosition: MutableLiveData<Int> = MutableLiveData()

    override val currencyTypePosition: MutableLiveData<Int> = MutableLiveData()

    override val loadCommand: IAsyncCommand = commandFactory.loadCommand

    override val filterCommand: ICommand = commandFactory.filterCommand

    override val setupCommand: ICommand = commandFactory.setupCommand

    override fun onCleared() {
        super.onCleared()
        closeScope()
        commandFactory.dispose()
    }
}