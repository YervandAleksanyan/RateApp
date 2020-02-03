package com.task.ratesapp.core.models

data class Bank(
    val key: String,
    val title: String,
    val rates: List<Rate>
)

