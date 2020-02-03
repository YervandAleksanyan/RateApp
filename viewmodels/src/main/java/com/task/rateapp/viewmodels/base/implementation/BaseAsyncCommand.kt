package com.task.rateapp.viewmodels.base.implementation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.animalsapp.viewmodel.base.implementation.BaseCommand
import com.task.animalsapp.viewmodel.utils.getString
import com.task.rateapp.viewmodels.R
import com.task.rateapp.viewmodels.base.IBlockingAsyncCommand
import com.task.rateapp.viewmodels.base.IDisposableAsyncCommand
import com.task.rateapp.viewmodels.base.ImmutableLiveData
import kotlinx.coroutines.*

abstract class BaseAsyncCommand : BaseCommand(), IDisposableAsyncCommand, IBlockingAsyncCommand {

    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private val jobs: MutableList<Job> = ArrayList()

    private val isBusyMutable = MutableLiveData(false)
    final override val isBusy: LiveData<Boolean> = ImmutableLiveData(isBusyMutable)

    private val isSuccessfulMutable = MutableLiveData(false)
    final override val isSuccessful: LiveData<Boolean> = ImmutableLiveData(isSuccessfulMutable)

    private var failureMessageMutable = MutableLiveData<String?>()
    final override val failureMessage: LiveData<String?> = ImmutableLiveData(failureMessageMutable)

    protected abstract suspend fun executeCoreAsync(): Boolean

    internal var exception: Throwable? = null

    override var isBlocked: Boolean = false

    final override suspend fun executeAsync() {
        if (isExecutable.value == true) {
            resetState()
            try {
                setState(State.BUSY)
                val result: Boolean = if (isBlocked) {
                    runBlocking {
                        executeCoreAsync()
                    }
                } else {
                    executeCoreAsync()
                }
                setState(State.FINISHED, successful = result)
            } catch (ex: Exception) {
                setState(State.FAILED)
                handleError(ex)
            }
        }
    }

    final override fun executeCore() {
        jobs += scope.launch {
            executeAsync()

            coroutineContext[Job]?.let { currentJob ->
                jobs -= currentJob
            }
        }
    }

    private fun resetState() {
        isBusyMutable.value = false
        isSuccessfulMutable.value = false
        exception = null
        failureMessageMutable.value = null
    }

    private fun setState(
        state: State,
        successful: Boolean = false
    ) {
        when (state) {
            State.BUSY -> isBusyMutable.value = true
            State.FINISHED -> {
                isBusyMutable.value = false
                isSuccessfulMutable.value = successful
            }
            State.FAILED -> {
                isBusyMutable.value = false
            }
            State.IDLE -> {
            }
        }
    }

    protected open suspend fun handleError(ex: Throwable) {
        exception = ex
        failureMessageMutable.value =
            getString(R.string.unknown_error)
    }

    protected fun setFailureMessage(failureMessage: String) {
        failureMessageMutable.value = failureMessage
    }

    override fun dispose() {
        cancelJobs()
    }

    override fun cancelJobs() {
        for (index in 0 until jobs.size) {
            jobs[index].cancel()
        }
    }

    private enum class State {
        IDLE,
        BUSY,
        FINISHED,
        FAILED
    }
}