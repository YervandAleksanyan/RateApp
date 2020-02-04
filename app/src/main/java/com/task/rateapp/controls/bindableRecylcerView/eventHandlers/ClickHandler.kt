package com.task.rateapp.controls.bindableRecylcerView.eventHandlers

import android.view.View

interface ClickHandler<T> {
    fun onClick(viewModel: T?, view: View)
}