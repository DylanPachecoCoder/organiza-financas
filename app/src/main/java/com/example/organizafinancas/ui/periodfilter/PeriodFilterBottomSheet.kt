package com.example.organizafinancas.ui.periodfilter

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.organizafinancas.commons.extensions.getDataRange
import com.example.organizafinancas.databinding.ModalBottomSheetContentBinding
import com.example.organizafinancas.domain.model.PaymentTypeFilter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.datepicker.MaterialDatePicker

class PeriodFilterBottomSheet(private val onDismiss: () -> Unit) : BottomSheetDialogFragment() {

    private var _binding: ModalBottomSheetContentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<PeriodFilterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ModalBottomSheetContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        BottomSheetBehavior.from(binding.standardBottomSheet).apply {
            state = BottomSheetBehavior.STATE_EXPANDED
            isDraggable = false
        }
    }

    override fun onStart() {
        super.onStart()
        setupObservers()
        setupListeners()
        viewModel.fetchFilterList()
    }

    private fun setupListeners() {
        binding.buttonPeriodFilter.setOnClickListener {
            dismiss()
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismiss()
    }

    private fun setupObservers() {
        viewModel.filterList.observe(viewLifecycleOwner) {
            binding.recyclerviewPeriodFilter.adapter = PeriodFilterAdapter(it, ::setupDataPicker)

        }
    }

    private fun setupDataPicker(filter: PaymentTypeFilter, position: Int) {
        MaterialDatePicker.Builder.dateRangePicker()
            .setTitleText(filter.name)
            .setPositiveButtonText(DATA_PICKER_POSITIVE_BUTTON)
            .setSelection(filter.getDataRange())
            .build().apply {
                addOnPositiveButtonClickListener {
                    viewModel.changeDate(it.first, it.second, filter)
                    binding.recyclerviewPeriodFilter.adapter?.notifyItemChanged(position)
                }
            }.show(parentFragmentManager, DATA_PICKER_TAG)
    }

    companion object {
        const val BOTTOM_SHEET_TAG = "ModalBottomSheet"
        private const val DATA_PICKER_TAG = "tag"
        private const val DATA_PICKER_POSITIVE_BUTTON = "confirmar"
    }
}