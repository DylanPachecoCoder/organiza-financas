package com.example.organizafinancas.data.repository

import com.example.organizafinancas.domain.model.PaymentTypeFilter
import kotlinx.coroutines.flow.Flow

interface PaymentTypeRepository {

    fun fetchPaymentTypeFilters(): Flow<List<PaymentTypeFilter>>
}
