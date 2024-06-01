package com.example.organizafinancas.data.repository

import com.example.organizafinancas.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    fun fetchCategoryFilters(): Flow<List<Category>>

    fun saveCategory(category: Category)

    fun deleteCategory(category: Category)

    fun updateCategory(category: Category)
}