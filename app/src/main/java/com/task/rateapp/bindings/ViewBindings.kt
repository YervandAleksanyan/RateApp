package com.task.rateapp.bindings

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.task.rateapp.R
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

@BindingAdapter(value = ["isInvisible"])
fun bindViewInvisibility(view: View, isInvisible: Any?) {
    val visible = getVisibility(isInvisible)
    view.visibility = if (visible) View.INVISIBLE else View.VISIBLE
}

@BindingAdapter("isSelected")
fun isSelected(view: TextView, maxValue: String) {
    if (maxValue == view.text) {
        view.setTextColor(view.context.getColor(R.color.colorPrimary))
    } else {
        view.setTextColor(view.context.getColor(R.color.colorAccent))
    }
}

@BindingAdapter("sortButtonIsSelected")
fun sortButtonIsSelected(view: TextView, value: Boolean?) {
    if (value != null) {
        if (value) {
            view.setCompoundDrawablesWithIntrinsicBounds(
                0,
                0,
                R.drawable.ic_arrow_upward,
                0
            )
        } else {
            view.setCompoundDrawablesWithIntrinsicBounds(
                0,
                0,
                R.drawable.ic_arrow_downward,
                0
            )
        }
    } else {
        view.setCompoundDrawablesWithIntrinsicBounds(
            0,
            0,
            R.drawable.ic_arrow_downward,
            0
        )
    }
}

private fun getVisibility(visibility: Any?): Boolean {
    var isVisible = true
    when (visibility) {
        null -> isVisible = false
        is Boolean -> isVisible = (visibility as Boolean?)!!
        is String -> isVisible = visibility.isNotEmpty()
        is Collection<*> -> {
            isVisible = visibility.isNotEmpty()
        }
    }
    return isVisible
}


