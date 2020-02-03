package com.task.rateapp.viewmodels.base.implementation

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.task.rateapp.viewmodels.base.ISelectableViewModel

open class BaseSelectableViewModel(selected: Boolean = false) : BaseObservable(),
    ISelectableViewModel {

    override var isSelected = selected
        @Bindable get() = field
        @Bindable set(value) {
            field = value
            notifyPropertyChanged(BR.selected)
        }
}