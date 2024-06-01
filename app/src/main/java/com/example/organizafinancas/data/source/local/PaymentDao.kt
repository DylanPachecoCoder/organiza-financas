package com.example.organizafinancas.data.source.local

import com.example.organizafinancas.domain.model.Payment
import com.example.organizafinancas.domain.model.PaymentType

interface PaymentDao {

    fun getAll(): List<Payment>

    fun getByPaymentType(paymentTypeList: List<PaymentType>): List<Payment>
}