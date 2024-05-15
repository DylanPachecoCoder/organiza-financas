package com.example.organizafinancas.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.organizafinancas.databinding.FragmentCategoryBinding
import com.example.organizafinancas.domain.model.SelectableFilter
import com.example.organizafinancas.ui.base.BaseFragment
import com.example.organizafinancas.ui.periodfilter.PeriodFilterBottomSheet

class CategoryFragment : BaseFragment<FragmentCategoryBinding>() {

    private val viewModel by viewModels<CategoryViewModel>()

    override fun inflateViewBind(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCategoryBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchCategories()
        setupObservers()
        setupListeners()
    }

    private fun setupListeners() {
        binding.buttonCategoryNew.setOnClickListener {
            showCategoryBottomSheet(onConfirmButton = viewModel::saveCategory)
        }
    }

    private fun showCategoryBottomSheet(
        category: SelectableFilter? = null,
        onConfirmButton: (SelectableFilter?) -> Unit,
        onDeleteButton: (SelectableFilter?) -> Unit = {}
    ) {
        CategoryBottomSheet(
            category,
            onConfirmButton,
            onDeleteButton
        ).show(parentFragmentManager, PeriodFilterBottomSheet.BOTTOM_SHEET_TAG)
    }

    private fun setupObservers() {
        viewModel.categories.observe(viewLifecycleOwner) {
            setupList(it)
        }
    }

    private fun setupList(categoryList: List<SelectableFilter>) {
        binding.recyclerviewCategory.adapter = CategoryAdapter(categoryList) {
//            showCategoryBottomSheet(it){
//
//            }
        }
    }
}