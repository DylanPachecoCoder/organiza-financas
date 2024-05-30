package com.example.organizafinancas.data.repository

import com.example.organizafinancas.data.source.local.PaymentTypeDao
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PaymentTypeRepositoryImpl @Inject constructor(
    private val dao: PaymentTypeDao
) : PaymentTypeRepository {

    override fun fetchPaymentTypeFilters() = flow { emit(dao.getAll()) }
}