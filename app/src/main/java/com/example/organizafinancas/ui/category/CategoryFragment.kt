package com.example.organizafinancas.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.organizafinancas.databinding.FragmentCategoryBinding
import com.example.organizafinancas.domain.model.SelectableFilter
import com.example.organizafinancas.ui.periodfilter.PeriodFilterBottomSheet

class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<CategoryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchCategories()
        setupObservers()
        setupListeners()
    }

    private fun setupListeners() {
        binding.button.setOnClickListener {
            showCategoryBottomSheet(
//                onConfirmButton = {}
            )
        }
    }

    private fun showCategoryBottomSheet(
        category: SelectableFilter? = null,
//        onConfirmButton: () -> SelectableFilter,
//        onDeleteButton: (() -> SelectableFilter)? = null
    ) {
        CategoryBottomSheet(
            category,
//            onConfirmButton,
//            onDeleteButton
        ).show(parentFragmentManager, PeriodFilterBottomSheet.BOTTOM_SHEET_TAG)
    }

    private fun setupObservers() {
        viewModel.categories.observe(viewLifecycleOwner) {
            setupList(it)
        }
    }

    private fun setupList(categoryList: List<SelectableFilter>) {
        binding.recyclerviewCategory.adapter = CategoryAdapter(categoryList) {
            showCategoryBottomSheet(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}