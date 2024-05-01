package com.example.organizafinancas.domain.model

import com.example.organizafinancas.domain.enums.PaymentTypeEnum
import java.time.LocalDate

data class Payment(
    val name: String = "Mercado pago",
    val type: PaymentTypeEnum = PaymentTypeEnum.CREDITO,
    val date: LocalDate = LocalDate.now(),
    val value: String = "R$ 20,00",
): Comparable<Payment>{
    override fun compareTo(other: Payment) = this.date.compareTo(other.date)
}
