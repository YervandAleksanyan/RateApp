package com.task.ratesapp.core.services

import com.task.ratesapp.core.models.Response
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface IRateAppRetorfitService {

    @GET("rates.ashx")
    fun getBanksListAsync(): Deferred<Response>

}