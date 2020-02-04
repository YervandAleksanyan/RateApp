package com.task.rateapp.viewmodels.base.implementation

import androidx.lifecycle.ViewModel
import com.task.animalsapp.viewmodel.base.implementation.BaseCommand
import com.task.rateapp.viewmodels.base.ICommand
import com.task.ratesapp.core.utils.IDisposable
import com.task.ratesapp.core.utils.Scoped
import com.task.ratesapp.core.utils.weak
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.parameter.parametersOf
import kotlin.reflect.KClass

internal abstract class BaseCommandFactory(
    viewModel: ViewModel
) : IDisposable, Scoped {

    private val viewModel by weak(viewModel)
    private val disposableCommands = HashMap<KClass<out Any>, IDisposable>()

    protected inline fun <reified T : BaseCommand> getCommand(
        noinline parameters: ParametersDefinition? = null
    ) = scope.get<T>(parameters = initCommandParameters(parameters))
        .also { command ->
            addDisposable(command)
        }

    private fun initCommandParameters(parameters: ParametersDefinition?): ParametersDefinition {
        val parametersValues: Array<out Any?> = parameters?.invoke()?.values ?: emptyArray()
        return {
            parametersOf(*parametersValues, viewModel!!)
        }
    }

    private fun addDisposable(command: ICommand) {
        if (command is IDisposable) {
            val key = command::class

            with(disposableCommands) {
                get(key)?.dispose()
                put(key, command)
            }
        }
    }

    override fun dispose() {
        disposeCommands()
        closeScope()
    }

    private fun disposeCommands() {
        with(disposableCommands) {
            values.forEach { it.dispose() }
            clear()
        }
    }
}