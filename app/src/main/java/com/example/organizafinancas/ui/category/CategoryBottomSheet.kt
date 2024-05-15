package com.example.organizafinancas.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.organizafinancas.R
import com.example.organizafinancas.databinding.BottomSheetCategoryBinding
import com.example.organizafinancas.domain.model.SelectableFilter
import com.example.organizafinancas.ui.base.BaseBottomSheet

class CategoryBottomSheet(
    private val category: SelectableFilter? = null,
    private val onConfirmButton: (SelectableFilter?) -> Unit,
    private val onDeleteButton: (SelectableFilter?) -> Unit
) : BaseBottomSheet<BottomSheetCategoryBinding>() {

    override val standardBottomSheet by lazy {
        binding.framelayoutCategory
    }

    override fun inflateViewBind(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = BottomSheetCategoryBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupView()
    }

    private fun setupView() {
        if (category != null) {
            setupEditCategory()
        } else {
            setupNewCategory()
        }
    }

    private fun setupListeners() {
        with(binding){
            buttonCategoryDelete.setOnClickListener {
                onDeleteButton(category)
                dismiss()
            }
            buttonCategorySave.setOnClickListener {
                val categoryName = edittextCategoryName.editText?.text.toString()
                val newCategory = SelectableFilter(categoryName)
                onConfirmButton(newCategory)
                dismiss()
            }
        }
    }

    private fun setupNewCategory() {
        binding.bottomsheetCategoryTitle.text =
            context?.getString(R.string.category_bottomsheet_title_new)
    }

    private fun setupEditCategory() {
        with(binding){
            edittextCategoryName.editText?.setText(category?.name)
            buttonCategoryDelete.isVisible = true
            bottomsheetCategoryTitle.text =
                context?.getString(R.string.category_bottomsheet_title_edit)
        }
    }
}