package com.task.rateapp.screens.banks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.rateapp.BR
import com.task.rateapp.R
import com.task.rateapp.controls.bindableRecylcerView.bindings.ItemBinder
import com.task.rateapp.controls.bindableRecylcerView.eventHandlers.ClickHandler
import com.task.rateapp.databinding.FragmentBanksBinding
import com.task.rateapp.utils.MarginItemDecoration
import com.task.rateapp.viewmodels.bankdetails.implementation.BankDetailsArgument
import com.task.rateapp.viewmodels.banks.IBanksViewModel
import com.task.rateapp.viewmodels.banks.implementation.BankItemViewModel
import com.task.ratesapp.core.models.Currency
import kotlinx.android.synthetic.main.fragment_banks.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class BanksFragment : Fragment(), ItemBinder<BankItemViewModel> {

    private lateinit var binding: FragmentBanksBinding

    private var cashTypeSelectListener: AdapterView.OnItemSelectedListener? =
        object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, positon: Int, p3: Long) {
                if (viewModel.cashTypePosition.value != positon) {
                    viewModel.cashTypePosition.value = positon
                }
            }

        }

    private var currencyTypeSelectListener: AdapterView.OnItemSelectedListener? =
        object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, positon: Int, p3: Long) {
                if (viewModel.currencyTypePosition.value != positon) {
                    viewModel.currencyTypePosition.value = positon
                }
            }
        }

    private val viewModel: IBanksViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_banks, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBindings()
        setupSpinners()
        setupSortButtons()
        initRv()
        viewModel.setupCommand.execute()
        if (savedInstanceState == null) {
            viewModel.loadCommand.execute()
        }
    }

    override fun onDetach() {
        super.onDetach()
        cashTypeSelectListener = null
        currencyTypeSelectListener = null
    }

    override fun getBindingVariable(model: BankItemViewModel): Int = BR.itemViewModel

    override fun getLayoutRes(model: BankItemViewModel): Int = R.layout.cell_bank

    private fun initBindings() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.view = this
    }

    private fun setupSpinners() {
        cash_type_spinner.adapter = ArrayAdapter.createFromResource(
            context!!,
            R.array.cash_types,
            android.R.layout.simple_list_item_1
        )
        cash_type_spinner.onItemSelectedListener = cashTypeSelectListener
        currency_type_spinner.adapter = ArrayAdapter(
            context!!,
            android.R.layout.simple_list_item_1,
            Currency.values()
        )
        currency_type_spinner.onItemSelectedListener = currencyTypeSelectListener
    }

    fun getBankClickHandler(): ClickHandler<BankItemViewModel> =
        object : ClickHandler<BankItemViewModel> {
            override fun onClick(viewModel: BankItemViewModel?, view: View) {
                binding.root.findNavController().navigate(
                    BanksFragmentDirections.actionBanksFragmentToBankDetailsFragment(
                        BankDetailsArgument(
                            name = viewModel?.name!!,
                            bankKey = viewModel.bankKey,
                            rates = viewModel.rates
                        )
                    )
                )
            }
        }

    private fun initRv() {
        banks_rv.layoutManager = LinearLayoutManager(context)
        banks_rv.addItemDecoration(
            MarginItemDecoration(
                context?.resources?.getDimension(R.dimen.list_view_vertical_margin)!!.toInt(),
                context?.resources?.getDimension(R.dimen.list_view_horizontal_margin)!!.toInt()
            )
        )
    }

    private fun setupSortButtons() {
        sell_sort_btn.setOnClickListener {
            viewModel.sortBySellOrder.value = !(viewModel.sortBySellOrder.value ?: false)

            viewModel.sortByBuyOrder.value = null
        }
        buy_sort_btn.setOnClickListener {
            viewModel.sortByBuyOrder.value = !(viewModel.sortByBuyOrder.value ?: false)
            viewModel.sortBySellOrder.value = null
        }
    }


}