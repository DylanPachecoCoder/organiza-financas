package com.example.organizafinancas.domain.model

import com.example.organizafinancas.domain.enums.PaymentTypeEnum
import java.time.LocalDate

data class PaymentFilter(
    val type: PaymentTypeEnum,
    val initialDate: LocalDate,
    val finishDate: LocalDate
): Comparable<PaymentFilter>{
    override fun compareTo(other: PaymentFilter) = this.type.compareTo(other.type)
}