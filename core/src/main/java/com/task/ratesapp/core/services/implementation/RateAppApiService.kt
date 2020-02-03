package com.task.ratesapp.core.services.implementation

import com.task.ratesapp.core.models.Response
import com.task.ratesapp.core.services.IRateAppApiService
import com.task.ratesapp.core.services.IRateAppRetorfitService
import kotlinx.coroutines.Deferred

class RateAppApiService(private val retrofitService: IRateAppRetorfitService) : IRateAppApiService {
    override suspend fun getBanksListAsync(): Deferred<Response> =
        retrofitService.getBanksListAsync()

}