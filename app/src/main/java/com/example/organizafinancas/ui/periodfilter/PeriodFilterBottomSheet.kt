package com.example.organizafinancas.ui.periodfilter

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.organizafinancas.commons.extensions.getDataRange
import com.example.organizafinancas.databinding.BottomSheetPeriodFilterBinding
import com.example.organizafinancas.domain.model.PaymentType
import com.example.organizafinancas.ui.base.BaseBottomSheet
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeriodFilterBottomSheet(
    private val onDismiss: () -> Unit = {}
) : BaseBottomSheet<BottomSheetPeriodFilterBinding>() {

    private val viewModel by viewModels<PeriodFilterViewModel>()
    override val standardBottomSheet by lazy { binding.framelayoutBottomSheet }

    override fun inflateViewBind(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = BottomSheetPeriodFilterBinding.inflate(inflater, container, false)

    override fun onStart() {
        super.onStart()
        setupObservers()
        setupListeners()
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

    private fun setupDataPicker(filter: PaymentType, position: Int) {
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