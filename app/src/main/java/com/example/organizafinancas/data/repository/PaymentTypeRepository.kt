package com.example.organizafinancas.data.repository

import com.example.organizafinancas.domain.model.PaymentType
import kotlinx.coroutines.flow.Flow

interface PaymentTypeRepository {

    suspend fun fetchPaymentTypes(): Flow<List<PaymentType>>

    suspend fun savePaymentType(paymentType: PaymentType)
}
