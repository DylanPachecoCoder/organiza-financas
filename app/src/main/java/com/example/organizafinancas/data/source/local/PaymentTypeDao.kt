package com.example.organizafinancas.data.source.local

import com.example.organizafinancas.domain.model.PaymentTypeFilter

interface PaymentTypeDao {

    fun getAll(): List<PaymentTypeFilter>
}
