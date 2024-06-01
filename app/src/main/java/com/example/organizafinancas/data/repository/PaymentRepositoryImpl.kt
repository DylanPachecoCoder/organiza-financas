package com.example.organizafinancas.data.repository

import com.example.organizafinancas.data.source.local.PaymentDao
import com.example.organizafinancas.domain.model.PaymentType
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaymentRepositoryImpl @Inject constructor(
    private val dao: PaymentDao,
) : PaymentRepository {

    override fun getAll() = flow { emit(dao.getAll()) }

    override fun getByPaymentType(paymentTypeList: List<PaymentType>) = flow {
        emit(dao.getByPaymentType(paymentTypeList))
    }
}