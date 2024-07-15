package com.example.organizafinancas.data.repository

import com.example.organizafinancas.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    suspend fun fetchCategoryFilters(): Flow<List<Category>>

    suspend fun saveCategory(category: Category)

    suspend fun deleteCategory(category: Category)
}