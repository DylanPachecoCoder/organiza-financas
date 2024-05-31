package com.example.organizafinancas.data.repository

import com.example.organizafinancas.domain.model.SelectableFilter
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    fun fetchCategoryFilters(): Flow<List<SelectableFilter>>

    fun saveCategory(category: SelectableFilter)

    fun deleteCategory(category: SelectableFilter)

    fun updateCategory(category: SelectableFilter)
}