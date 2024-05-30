package com.example.organizafinancas.data.repository

import com.example.organizafinancas.data.source.local.CategoryDao
import com.example.organizafinancas.data.source.local.PaymentDao
import com.example.organizafinancas.data.source.local.PaymentTypeDao
import com.example.organizafinancas.domain.model.SelectableFilter
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val paymentDao: PaymentDao,
    private val categoryDao: CategoryDao,
    private val paymentTypeDao: PaymentTypeDao
) : Repository {

    override fun fetchFilters() = flow {
        val selectableFilters = mutableListOf<SelectableFilter>().apply {
            addAll(paymentTypeDao.getAll())
            addAll(categoryDao.getAll())
        }
        emit(selectableFilters)
    }

    override fun fetchPayments() = flow {
        val payments = paymentDao.getPayments(paymentTypeDao.getAll(), categoryDao.getAll())
        emit(payments)
    }

    override fun fetchPaymentFilters() = flow { emit(paymentTypeDao.getAll()) }

    override fun fetchCategoryFilters() = flow { emit(categoryDao.getAll()) }

    override fun saveCategory(category: SelectableFilter?) = categoryDao.save(category)

    override fun deleteCategory(category: SelectableFilter?) = categoryDao.delete(category)

    override fun updateCategory(category: SelectableFilter?) = categoryDao.update(category)
}