package com.task.ratesapp.core.services


interface IDialogService {
    fun displayAlert(
        title: String?,
        message: String?,
        acceptButton: String? = null
    )
}