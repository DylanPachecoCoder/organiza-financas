package com.example.organizafinancas.data.repository

import com.example.organizafinancas.data.source.local.CategoryDao
import com.example.organizafinancas.domain.model.Category
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val dao: CategoryDao
) : CategoryRepository {

    override fun fetchCategoryFilters() = flow {
        emit(dao.getAll())
    }

    override fun saveCategory(category: Category) {
        dao.save(category)
    }

    override fun deleteCategory(category: Category) {
        dao.delete(category)
    }

    override fun updateCategory(category: Category) {
        dao.update(category)
    }
}