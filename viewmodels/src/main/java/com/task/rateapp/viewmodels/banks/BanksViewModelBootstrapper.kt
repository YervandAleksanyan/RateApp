package com.task.rateapp.viewmodels.banks

import com.task.rateapp.viewmodels.banks.implementation.*
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

object BanksViewModelBootstrapper {
    val module: Module
        get() = module {
            viewModel<IBanksViewModel> { BanksViewModel() }

            scope(named<BanksViewModel>()) {
                scoped { (viewModel: IBanksViewModel) ->
                    BanksViewModelCommandFactory(
                        viewModel
                    )
                }
            }

            scope(named<BanksViewModelCommandFactory>()) {
                scoped { (viewModel: BanksViewModel) ->
                    LoadBanksCommand(
                        viewModel,
                        get()
                    )
                }

                scoped { (viewModel: BanksViewModel) ->
                    FilterBanksCommand(
                        viewModel
                    )
                }

                scoped { (viewModel: BanksViewModel) ->
                    SetupBanksViewModelCommand(
                        viewModel
                    )
                }
            }
        }
}