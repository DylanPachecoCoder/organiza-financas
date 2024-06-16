package com.example.organizafinancas.data.repository

import com.example.organizafinancas.data.source.local.PaymentTypeDao
import com.example.organizafinancas.domain.model.PaymentType
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PaymentTypeRepositoryImpl @Inject constructor(
    private val dao: PaymentTypeDao
) : PaymentTypeRepository {

    override suspend fun fetchPaymentTypes() = dao.getAll().flowOn(IO)

    override suspend fun savePaymentType(paymentType: PaymentType) {
        withContext(IO) {
            dao.insertAll(paymentType)
        }
    }
}