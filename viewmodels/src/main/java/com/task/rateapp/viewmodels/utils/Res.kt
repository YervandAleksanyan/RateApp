package com.task.animalsapp.viewmodel.utils

import android.content.Context
import android.content.res.Resources
import androidx.annotation.StringRes
import com.task.animalsapp.viewmodel.utils.Res.resources
import org.koin.core.KoinComponent
import org.koin.core.get

internal object Res : KoinComponent {
    val resources: Resources
        get() = get<Context>().resources
}

fun getString(@StringRes resId: Int, vararg args: Any): String = resources.getString(resId, *args)