package com.example.organizafinancas.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.organizafinancas.R
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
        if (category != null) {
            setupEditCategory(category)
        } else {
            setupNewCategory()
        }
    }

    private fun setupNewCategory() {
        binding.bottomsheetTitle.text =
            context?.getString(R.string.category_bottomsheet_title_new)
    }

    private fun setupEditCategory(category: SelectableFilter) {
        binding.textField.editText?.setText(category.name)
        binding.bottomsheetTitle.text =
            context?.getString(R.string.category_bottomsheet_title_edit)
    }
}