package com.task.rateapp.screens.bankdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.rateapp.BR
import com.task.rateapp.R
import com.task.rateapp.controls.bindableRecylcerView.bindings.ItemBinder
import com.task.rateapp.databinding.FragmentBankDetailsBinding
import com.task.rateapp.utils.MarginItemDecoration
import com.task.rateapp.viewmodels.bankdetails.IBankDetailsViewModel
import com.task.rateapp.viewmodels.bankdetails.implementation.RateItemViewModel
import com.task.ratesapp.core.models.CashType
import kotlinx.android.synthetic.main.fragment_bank_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class BankDetailsFragment : Fragment(), ItemBinder<RateItemViewModel> {

    private lateinit var binding: FragmentBankDetailsBinding

    private val viewModel: IBankDetailsViewModel by viewModel()

    private val args: BankDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_bank_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBindings()
        initRv()
        initRadioGroup()
        viewModel.bankDetails.value = args.bankDetails
        viewModel.setupCommand.execute()
        viewModel.loadCommand.execute()
    }

    override fun getLayoutRes(model: RateItemViewModel): Int = R.layout.cell_rate

    override fun getBindingVariable(model: RateItemViewModel): Int = BR.itemRate

    private fun initBindings() {
        binding.viewModel = viewModel
        binding.view = this
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun initRv() {
        rates_rv.layoutManager = LinearLayoutManager(context)
        rates_rv.addItemDecoration(
            MarginItemDecoration(
                context?.resources?.getDimension(R.dimen.list_view_vertical_margin)!!.toInt(),
                context?.resources?.getDimension(R.dimen.list_view_horizontal_margin)!!.toInt()
            )
        )
    }

    private fun initRadioGroup() {
        cash_button.isChecked = true
        viewModel.isCash.value = CashType.CASH
        cash_views_group.setOnCheckedChangeListener { _, i ->
            viewModel.isCash.value = if (i == R.id.cash_button) {
                CashType.CASH
            } else {
                CashType.NON_CASH
            }
        }
    }
}