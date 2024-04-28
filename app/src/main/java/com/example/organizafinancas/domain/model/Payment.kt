package com.example.organizafinancas.domain.model

import com.example.organizafinancas.domain.enums.PaymentTypeEnum

data class Payment(
    val name: String = "Mercado pago",
    val type: PaymentTypeEnum = PaymentTypeEnum.CREDITO,
    val date: String = "10/04/24",
    val value: String = "R$ 20,00",
)
