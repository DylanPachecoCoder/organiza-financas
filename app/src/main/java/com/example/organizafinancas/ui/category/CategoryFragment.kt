package com.example.organizafinancas.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.organizafinancas.databinding.FragmentCategoryBinding
import com.example.organizafinancas.domain.model.Category
import com.example.organizafinancas.ui.base.BaseFragment
import com.example.organizafinancas.ui.periodfilter.PeriodFilterBottomSheet
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding>() {

    private val viewModel by viewModels<CategoryViewModel>()
    private val adapter by lazy {
        CategoryAdapter {
            showCategoryBottomSheet(
                it,
                viewModel::updateCategory,
                viewModel::deleteCategory,
                viewModel::fetchCategories
            )
        }
    }

    override fun inflateViewBind(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentCategoryBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCollectors()
        setupListeners()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchCategories()
    }

    private fun setupRecyclerView() {
        binding.recyclerviewCategory.adapter = adapter
    }

    private fun setupListeners() {
        binding.buttonCategoryNew.setOnClickListener {
            showCategoryBottomSheet(
                onConfirmButton = viewModel::saveCategory,
                onDismiss = viewModel::fetchCategories
            )
        }
    }

    private fun showCategoryBottomSheet(
        category: Category? = null,
        onConfirmButton: (Category) -> Unit,
        onDeleteButton: (Category) -> Unit = {},
        onDismiss: () -> Unit = {}
    ) {
        CategoryBottomSheet(category, onConfirmButton, onDeleteButton, onDismiss)
            .show(parentFragmentManager, PeriodFilterBottomSheet.BOTTOM_SHEET_TAG)
    }

    private fun setupCollectors() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest(::setupUiState)
            }
        }
    }

    private fun setupUiState(uiState: UiState) {
        refreshList(uiState.categories)
    }

    private fun refreshList(categoryList: List<Category>) {
        adapter.refreshList(categoryList)
    }
}