package com.example.organizafinancas.ui.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.organizafinancas.R
import com.example.organizafinancas.databinding.FragmentPaymentBinding
import com.example.organizafinancas.domain.model.Payment
import com.example.organizafinancas.domain.model.Filter
import com.example.organizafinancas.ui.base.BaseFragment
import com.example.organizafinancas.ui.periodfilter.PeriodFilterBottomSheet
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PaymentFragment : BaseFragment<FragmentPaymentBinding>() {

    private val viewModel by viewModels<PaymentViewModel>()
    private val filterAdapter by lazy { FilterAdapter(onItemClicked = viewModel::fetchPaymentList) }

    override fun inflateViewBind(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentPaymentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOptionsMenu()
        setupFilterAdapter()
        setupCollectors()
    }

    private fun setupFilterAdapter() {
        binding.recyclerviewFilterOption.adapter = filterAdapter
    }

    private fun setupCollectors() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect(::setupUiState)
            }
        }
    }

    private fun setupUiState(uiState: UiState) {
        setupFilterList(uiState.filters)
        setupPaymentList(uiState.payments)
        setupTotalValue(uiState.total)
    }

    private fun showEditPeriodBottomSheet() {
        PeriodFilterBottomSheet(viewModel::fetchPaymentList).show(
            parentFragmentManager,
            PeriodFilterBottomSheet.BOTTOM_SHEET_TAG
        )
    }

    private fun setupTotalValue(total: String) {
        binding.textviewTotalValue.text = total
    }

    private fun setupPaymentList(paymentList: List<Payment>?) {
        binding.recyclerviewPayment.adapter = PaymentAdapter(paymentList.orEmpty())
    }

    private fun setupFilterList(filterList: List<Filter>) {
        filterAdapter.refreshList(filterList)
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

    private fun setOptionsMenuAction(menuItem: MenuItem) = when (menuItem.itemId) {
        R.id.nav_period_filter -> {
            showEditPeriodBottomSheet()
            true
        }
        else -> false
    }
}