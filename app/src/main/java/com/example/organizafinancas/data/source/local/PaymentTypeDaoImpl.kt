package com.example.organizafinancas.data.source.local

import com.example.organizafinancas.domain.model.PaymentTypeFilter
import javax.inject.Inject

class PaymentTypeDaoImpl @Inject constructor() : PaymentTypeDao {
    override fun getAll() = paymentFilterList

    private val paymentFilterList =
        mutableListOf(
            PaymentTypeFilter.CreditFilter(),
            PaymentTypeFilter.CashFilter(),
        )
}