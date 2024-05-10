package com.example.organizafinancas.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.organizafinancas.R
import com.example.organizafinancas.commons.extensions.toCurrency
import com.example.organizafinancas.databinding.FragmentHomeBinding
import com.example.organizafinancas.domain.model.Payment
import com.example.organizafinancas.domain.model.SelectableFilter
import com.example.organizafinancas.ui.periodfilter.PeriodFilterBottomSheet

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setHasOptionsMenu(true)
        setupObservers()
        viewModel.fetchFilterList()
        viewModel.fetchPaymentList()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_period_filter -> {
                PeriodFilterBottomSheet(viewModel::fetchPaymentList)
                    .show(parentFragmentManager, PeriodFilterBottomSheet.BOTTOM_SHEET_TAG)
            }
        }
        return super.onOptionsItemSelected(item)
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
        binding.recyclerviewPurchase.adapter = PaymentAdapter(paymentList.orEmpty())
    }

    private fun setupFilterList(filterList: List<SelectableFilter>) {
        binding.recyclerviewFilterOption.adapter =
            FilterAdapter(filterList, viewModel::fetchPaymentList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}