package com.example.organizafinancas.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.organizafinancas.databinding.FragmentCategoryBinding
import com.example.organizafinancas.domain.model.SelectableFilter

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
    }

    private fun setupObservers() {
        viewModel.categories.observe(viewLifecycleOwner){
            setupList(it)
        }
    }

    private fun setupList(categoryList: List<SelectableFilter>) {
        binding.recyclerviewCategory.apply {
            val stub = (categoryList.size+1)/3
            layoutManager = StaggeredGridLayoutManager(stub, LinearLayoutManager.HORIZONTAL)
            adapter = CategoryAdapter(categoryList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}