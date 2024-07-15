package com.example.organizafinancas.data.repository

import com.example.organizafinancas.domain.model.PaymentMethod
import kotlinx.coroutines.flow.Flow

interface PaymentMethodRepository {

    suspend fun fetchPaymentTypes(): Flow<List<PaymentMethod>>

    suspend fun savePaymentType(paymentMethod: PaymentMethod)
}
