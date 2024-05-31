package com.example.organizafinancas.data.repository

import com.example.organizafinancas.data.source.local.CategoryDao
import com.example.organizafinancas.domain.model.SelectableFilter
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val dao: CategoryDao
) : CategoryRepository {

    override fun fetchCategoryFilters() = flow {
        emit(dao.getAll())
    }

    override fun saveCategory(category: SelectableFilter) {
        dao.save(category)
    }

    override fun deleteCategory(category: SelectableFilter) {
        dao.delete(category)
    }

    override fun updateCategory(category: SelectableFilter) {
        dao.update(category)
    }
}