package com.example.organizafinancas.data.repository

import com.example.organizafinancas.data.source.local.CategoryDao
import com.example.organizafinancas.domain.model.Category
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val dao: CategoryDao
) : CategoryRepository {

    override suspend fun fetchCategoryFilters() = dao.getAll().flowOn(IO)

    override suspend fun saveCategory(category: Category) {
        withContext(IO){
            dao.insertAll(category)
        }
    }

    override suspend fun deleteCategory(category: Category) {
        withContext(IO){
            dao.delete(category)
        }
    }
}