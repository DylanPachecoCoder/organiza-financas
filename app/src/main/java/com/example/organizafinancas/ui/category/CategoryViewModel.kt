package com.example.organizafinancas.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.organizafinancas.data.Repository
import com.example.organizafinancas.domain.model.SelectableFilter
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val repository: Repository = Repository.getInstance()
) : ViewModel() {

    private val _categories = MutableLiveData<MutableList<SelectableFilter>>()
    val categories: LiveData<MutableList<SelectableFilter>> = _categories

    fun fetchCategories() {
        viewModelScope.launch {
            _categories.value = repository.fetchCategoryFilters()
        }
    }

    fun saveCategory(category: SelectableFilter?) {
        _categories.value = repository.saveCategory(category)
    }
}