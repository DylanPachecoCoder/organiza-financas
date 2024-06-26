package com.example.organizafinancas.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.organizafinancas.commons.toCurrency
import com.example.organizafinancas.databinding.FragmentHomeBinding
import com.example.organizafinancas.domain.enums.PaymentTypeEnum
import com.example.organizafinancas.domain.model.Payment
import com.example.organizafinancas.ui.adapter.FilterAdapter
import com.example.organizafinancas.ui.adapter.PaymentAdapter
import java.text.NumberFormat

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
        setupObservers()
        viewModel.fetchFilterList()
        viewModel.fetchPaymentList()
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
        binding.recyclerviewPurchase.adapter = PaymentAdapter(paymentList ?: listOf())
    }

    private fun setupFilterList(filterList: List<PaymentTypeEnum>) {
        binding.recyclerviewFilterOption.adapter = FilterAdapter(filterList, viewModel::filterList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}