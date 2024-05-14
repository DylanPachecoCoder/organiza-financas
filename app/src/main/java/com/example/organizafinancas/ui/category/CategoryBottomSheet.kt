package com.example.organizafinancas.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.organizafinancas.databinding.BottomSheetCategoryBinding
import com.example.organizafinancas.domain.model.SelectableFilter
import com.example.organizafinancas.ui.base.BaseBottomSheet

class CategoryBottomSheet(
    private val category: SelectableFilter? = null,
//    private val onConfirmButton: () -> SelectableFilter,
//    private val onDeleteButton: (() -> SelectableFilter)? = null,
) : BaseBottomSheet<BottomSheetCategoryBinding>() {

    override val standardBottomSheet by lazy {
        binding.standardBottomSheet
    }

    override fun inflateViewBind(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = BottomSheetCategoryBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        category?.also {
            binding.textField.editText?.setText(it.name)
        }
    }
}