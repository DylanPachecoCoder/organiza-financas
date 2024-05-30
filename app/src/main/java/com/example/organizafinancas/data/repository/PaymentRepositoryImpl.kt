package com.example.organizafinancas.data.repository

import com.example.organizafinancas.data.source.local.CategoryDao
import com.example.organizafinancas.data.source.local.PaymentDao
import com.example.organizafinancas.data.source.local.PaymentTypeDao
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaymentRepositoryImpl @Inject constructor(
    private val paymentDao: PaymentDao,
    private val categoryDao: CategoryDao,
    private val paymentTypeDao: PaymentTypeDao
) : PaymentRepository {

    override fun fetchPayments() = flow {
        val payments = paymentDao.getPayments(paymentTypeDao.getAll(), categoryDao.getAll())
        emit(payments)
    }
}