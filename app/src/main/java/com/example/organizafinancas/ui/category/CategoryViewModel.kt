package com.example.organizafinancas.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.organizafinancas.data.repository.CategoryRepository
import com.example.organizafinancas.domain.model.SelectableFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: CategoryRepository) : ViewModel() {

    private val _categories = MutableLiveData<List<SelectableFilter>>()
    val categories: LiveData<List<SelectableFilter>> = _categories

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            repository.fetchCategoryFilters().collect{
                _categories.value = it
            }
        }
    }

    fun saveCategory(category: SelectableFilter?) {
        viewModelScope.launch {
            validateCategory(category) {
                _categories.value = repository.saveCategory(category)
            }
        }
    }

    fun deleteCategory(category: SelectableFilter?) {
        viewModelScope.launch {
            _categories.value = repository.deleteCategory(category)
        }
    }

    fun updateCategory(category: SelectableFilter?) {
        viewModelScope.launch {
            validateCategory(category) {
                _categories.value = repository.updateCategory(category)
            }
        }
    }

    private fun validateCategory(category: SelectableFilter?, block: () -> Unit) {
        if (category != null && category.name.isNotEmpty()) {
            block()
        }
    }
}