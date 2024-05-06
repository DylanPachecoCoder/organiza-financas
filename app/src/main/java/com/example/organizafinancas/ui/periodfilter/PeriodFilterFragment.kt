package com.example.organizafinancas.ui.periodfilter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.organizafinancas.commons.extensions.getDataRange
import com.example.organizafinancas.databinding.FragmentPeriodFilterBinding
import com.example.organizafinancas.domain.model.PaymentFilter
import com.google.android.material.datepicker.MaterialDatePicker

class PeriodFilterFragment : Fragment() {

    private var _binding: FragmentPeriodFilterBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy {
        ViewModelProvider(this)[PeriodFilterViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPeriodFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setHasOptionsMenu(true)
        setupObservers()
        setupListeners()
        viewModel.fetchFilterList()
    }

    private fun setupListeners() {
        binding.buttonPeriodFilter.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupObservers() {
        viewModel.filterList.observe(viewLifecycleOwner) {
            binding.recyclerviewPeriodFilter.adapter = PeriodFilterAdapter(it, ::setupDataPicker)
        }
    }

    private fun setupDataPicker(filter: PaymentFilter) {
        MaterialDatePicker.Builder.dateRangePicker()
            .setTitleText(filter.type.paymentType)
            .setPositiveButtonText(DATA_PICKER_POSITIVE_BUTTON)
            .setSelection(filter.getDataRange())
            .build().apply {
                addOnPositiveButtonClickListener {
                    viewModel.changeDate(it.first, it.second, filter)
                }
            }.show(parentFragmentManager, TAG)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.clear()
        super.onPrepareOptionsMenu(menu)
    }

    companion object {
        private const val TAG = "tag"
        private const val DATA_PICKER_POSITIVE_BUTTON = "confirmar"
    }
}