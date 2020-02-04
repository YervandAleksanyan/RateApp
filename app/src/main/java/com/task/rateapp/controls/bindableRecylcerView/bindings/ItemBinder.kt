package com.task.rateapp.controls.bindableRecylcerView.bindings

import com.task.rateapp.controls.bindableRecylcerView.ViewHolder

interface ItemBinder<T> {
    fun getLayoutRes(model: T): Int
    fun getBindingVariable(model: T): Int
    fun resetViewHolder(viewHolder: ViewHolder) {

    }
}
