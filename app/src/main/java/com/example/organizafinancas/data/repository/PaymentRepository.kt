package com.example.organizafinancas.data.repository

import com.example.organizafinancas.domain.model.Payment
import com.example.organizafinancas.domain.model.PaymentTypeFilter
import com.example.organizafinancas.domain.model.SelectableFilter
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface PaymentRepository  {

    fun getAll(): Flow<List<Payment>>

    fun getByPaymentType(paymentTypeList: List<PaymentTypeFilter>): Flow<List<Payment>>
}