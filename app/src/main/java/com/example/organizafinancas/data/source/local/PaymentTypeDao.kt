package com.example.organizafinancas.data.source.local

import com.example.organizafinancas.domain.model.PaymentType
import kotlinx.coroutines.flow.Flow

interface PaymentTypeDao {

    fun getAll(): Flow<List<PaymentType>>
}
