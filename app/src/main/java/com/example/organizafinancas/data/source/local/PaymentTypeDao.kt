package com.example.organizafinancas.data.source.local

import com.example.organizafinancas.domain.model.PaymentType

interface PaymentTypeDao {

    fun getAll(): List<PaymentType>
}
