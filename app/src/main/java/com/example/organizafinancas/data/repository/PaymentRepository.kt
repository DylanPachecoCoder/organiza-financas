package com.example.organizafinancas.data.repository

import com.example.organizafinancas.domain.model.Payment
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface PaymentRepository {

    suspend fun getAll(): Flow<List<Payment>>

    suspend fun getByPaymentType(): Flow<List<Payment>>

    suspend fun insert(payment: Payment)
}