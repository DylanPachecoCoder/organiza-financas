package com.example.organizafinancas.data.source.local

import com.example.organizafinancas.domain.model.Payment
import com.example.organizafinancas.domain.model.PaymentTypeFilter
import com.example.organizafinancas.domain.model.SelectableFilter

interface PaymentDao {

    fun getPayments(
        paymentFilterList: List<PaymentTypeFilter>,
        categoryFilterList: List<SelectableFilter>
    ): List<Payment>
}