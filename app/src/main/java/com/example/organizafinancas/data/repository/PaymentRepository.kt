package com.example.organizafinancas.data.repository

import com.example.organizafinancas.domain.model.Payment
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface PaymentRepository  {

    fun fetchPayments(): Flow<List<Payment>>
}