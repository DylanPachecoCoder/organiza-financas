package com.example.organizafinancas.data.repository

import com.example.organizafinancas.data.source.local.PaymentDao
import com.example.organizafinancas.domain.model.Payment
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaymentRepositoryImpl @Inject constructor(
    private val dao: PaymentDao,
) : PaymentRepository {

    override suspend fun getAll() = dao.getAll().flowOn(IO)

    override suspend fun getByPaymentType() = dao.getByPaymentTypeAndCategory().flowOn(IO)

    override suspend fun insert(payment: Payment) {
        withContext(IO) {
            dao.insertAll(payment)
        }
    }
}
