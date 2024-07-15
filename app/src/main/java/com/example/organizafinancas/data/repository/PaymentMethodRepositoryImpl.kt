package com.example.organizafinancas.data.repository

import com.example.organizafinancas.data.source.local.PaymentMethodDao
import com.example.organizafinancas.domain.model.PaymentMethod
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PaymentMethodRepositoryImpl @Inject constructor(
    private val dao: PaymentMethodDao
) : PaymentMethodRepository {

    override suspend fun fetchPaymentTypes() = dao.getAll().flowOn(IO)

    override suspend fun savePaymentType(paymentMethod: PaymentMethod) {
        withContext(IO) {
            dao.insertAll(paymentMethod)
        }
    }
}