package com.example.organizafinancas.ui.payment

import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.organizafinancas.R
import com.example.organizafinancas.commons.extensions.ONE
import com.example.organizafinancas.commons.extensions.toCurrency
import com.example.organizafinancas.databinding.FragmentPaymentBinding
import com.example.organizafinancas.domain.model.Payment
import com.example.organizafinancas.domain.model.SelectableFilter
import com.example.organizafinancas.ui.base.BaseFragment
import com.example.organizafinancas.ui.periodfilter.PeriodFilterBottomSheet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentFragment : BaseFragment<FragmentPaymentBinding>() {

    private val viewModel by viewModels<PaymentViewModel>()

    override fun inflateViewBind(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentPaymentBinding.inflate(inflater, container, false)

    override fun onStart() {
        super.onStart()
        setupOptionsMenu()
        setupObservers()
    }

    private fun setupOptionsMenu() {
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main, menu)
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean =
                setOptionsMenuAction(menuItem)
        })
    }

    private fun setOptionsMenuAction(menuItem: MenuItem) =
        when (menuItem.itemId) {
            R.id.nav_period_filter -> {
                showEditPeriodBottomSheet()
                true
            }
            else -> false
        }

    private fun showEditPeriodBottomSheet() {
        PeriodFilterBottomSheet(viewModel::fetchPaymentList)
            .show(parentFragmentManager, PeriodFilterBottomSheet.BOTTOM_SHEET_TAG)
    }

    private fun setupObservers() {
        viewModel.filteredPaymentList.observe(viewLifecycleOwner) {
            setupPaymentList(it)
            setupTotalValue()
        }
        viewModel.filterList.observe(viewLifecycleOwner) {
            setupFilterList(it)
        }
    }

    private fun setupTotalValue() {
        binding.textviewTotalValue.text = viewModel.sumValues().toCurrency()
    }

    private fun setupPaymentList(paymentList: List<Payment>?) {
        binding.recyclerviewPayment.adapter = PaymentAdapter(paymentList.orEmpty())
    }

    private fun setupFilterList(filterList: List<SelectableFilter>) {
        binding.recyclerviewFilterOption.apply {
            val listQuantityLines = (filterList.size + Int.ONE) / MAX_FILTERS_PER_LINE
            layoutManager =
                StaggeredGridLayoutManager(listQuantityLines, LinearLayoutManager.HORIZONTAL)
            adapter = FilterAdapter(filterList, viewModel::fetchPaymentList)
        }
    }

    companion object {
        private const val MAX_FILTERS_PER_LINE = 5
    }
}