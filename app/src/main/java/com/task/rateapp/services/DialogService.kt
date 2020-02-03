package com.task.rateapp.services

import android.content.Context
import com.afollestad.materialdialogs.MaterialDialog
import com.task.rateapp.RateApp
import com.task.ratesapp.core.services.IDialogService


class DialogService : IDialogService {

    override fun displayAlert(
        title: String?,
        message: String?,
        acceptButton: String?
    ) {
        if (getContext() == null) return
        MaterialDialog(getContext()!!).show {
            cancelable(false)
            title(text = title)
            message(text = message)
            acceptButton?.let {
                positiveButton(text = acceptButton)
            }
        }
    }

    private fun getContext(): Context? = RateApp.instance?.currentActivity

}