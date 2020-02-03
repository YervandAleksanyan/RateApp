package com.task.ratesapp.core.services

import com.task.ratesapp.core.models.Response
import kotlinx.coroutines.Deferred

interface IRateAppApiService {
    suspend fun getBanksListAsync(): Deferred<Response>
}