package com.example.organizafinancas.data.repository

import com.example.organizafinancas.domain.model.Payment
import com.example.organizafinancas.domain.model.PaymentType
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface PaymentRepository  {

    suspend fun getAll(): Flow<List<Payment>>

    suspend fun getByPaymentType(paymentTypeList: List<PaymentType>): Flow<List<Payment>>

    suspend fun insert(payment: Payment)
}