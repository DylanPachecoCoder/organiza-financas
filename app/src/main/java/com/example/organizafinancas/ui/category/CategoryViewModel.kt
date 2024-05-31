package com.example.organizafinancas.ui.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.organizafinancas.data.repository.CategoryRepository
import com.example.organizafinancas.domain.model.SelectableFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: CategoryRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    fun fetchCategories() {
        viewModelScope.launch {
            repository.fetchCategoryFilters().collect {
                _uiState.update { currentValue ->
                    currentValue.copy(categories = it)
                }
            }
        }
    }

    fun saveCategory(category: SelectableFilter) {
        viewModelScope.launch {
            repository.saveCategory(category)
        }
    }

    fun deleteCategory(category: SelectableFilter) {
        viewModelScope.launch {
            repository.deleteCategory(category)
        }
    }

    fun updateCategory(category: SelectableFilter) {
        viewModelScope.launch {
            repository.updateCategory(category)
        }
    }
}

data class UiState(
    val categories: List<SelectableFilter> = emptyList()
)