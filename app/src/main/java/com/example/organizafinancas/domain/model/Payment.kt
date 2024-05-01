package com.example.organizafinancas.domain.model

import com.example.organizafinancas.domain.enums.PaymentTypeEnum
import java.time.LocalDate

data class Payment(
    val name: String = "Mercado pago",
    val type: PaymentTypeEnum,
    val date: LocalDate = LocalDate.now(),
    val value: Double = 20.57,
): Comparable<Payment>{
    override fun compareTo(other: Payment) = this.date.compareTo(other.date)
}
