package com.example.organizafinancas.data.repository

import com.example.organizafinancas.data.source.local.PaymentDao
import com.example.organizafinancas.domain.model.Category
import com.example.organizafinancas.domain.model.Payment
import com.example.organizafinancas.domain.model.PaymentType
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaymentRepositoryImpl @Inject constructor(
    private val dao: PaymentDao,
) : PaymentRepository {

    override suspend fun getAll() = dao.getAll().flowOn(IO)

    override suspend fun getByPaymentType(
        paymentTypeList: List<PaymentType>,
        categoriesFilters: List<Category>
    ) = flow {
        val paymentList = mutableListOf<Payment>()
        paymentTypeList.forEach { paymentType ->
            val payments = dao.getByPaymentTypeAndCategory(
                paymentType.name,
                paymentType.initialDate,
                paymentType.finishDate,
                categoriesFilters
            )
            paymentList.addAll(payments)
        }
        emit(paymentList)
    }.flowOn(IO)

    override suspend fun insert(payment: Payment) {
        withContext(IO) {
            dao.insertAll(payment)
        }
    }
}
