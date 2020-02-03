package com.task.rateapp.bindings

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.task.rateapp.viewmodels.base.ICommand


@BindingAdapter(value = ["isBusy", "loadCommand", "reloadCommand"], requireAll = false)
fun setSwipeToRefreshIsBusy(
    swipeRefreshLayout: SwipeRefreshLayout,
    isBusy: LiveData<Boolean>,
    loadCommand: ICommand?,
    reloadCommand: ICommand?

) {
    swipeRefreshLayout.setOnRefreshListener {
        reloadCommand?.execute() ?: run {
            loadCommand?.execute()
        }
    }
    swipeRefreshLayout.isRefreshing = isBusy.value ?: true
}


