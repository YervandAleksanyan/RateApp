package com.task.rateapp.viewmodels.bankdetails

import com.task.rateapp.viewmodels.bankdetails.implementation.*
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

object BankDetailsViewModelBootstrapper {

    val module: Module
        get() = module {
            viewModel<IBankDetailsViewModel> { BankDetailsViewModel() }

            scope(named<BankDetailsViewModel>()) {
                scoped { (viewModel: IBankDetailsViewModel) ->
                    BankDetailsViewModelCommandFactory(
                        viewModel
                    )
                }
            }

            scope(named<BankDetailsViewModelCommandFactory>()) {
                scoped { (viewModel: BankDetailsViewModel) ->
                    LoadBankInformationCommand(
                        viewModel
                    )
                }
                scoped { (viewModel: BankDetailsViewModel) ->
                    FilterRatesCommand(
                        viewModel
                    )
                }

                scoped { (viewModel: BankDetailsViewModel) ->
                    SetupBankDetailsViewModelCommand(
                        viewModel
                    )
                }
            }

        }
}