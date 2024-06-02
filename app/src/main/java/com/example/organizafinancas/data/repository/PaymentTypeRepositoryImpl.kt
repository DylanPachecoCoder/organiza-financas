package com.example.organizafinancas.data.repository

import com.example.organizafinancas.data.source.local.PaymentTypeDao
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PaymentTypeRepositoryImpl @Inject constructor(
    private val dao: PaymentTypeDao
) : PaymentTypeRepository {

    override fun fetchPaymentTypeFilters() = dao.getAll().flowOn(IO)
}