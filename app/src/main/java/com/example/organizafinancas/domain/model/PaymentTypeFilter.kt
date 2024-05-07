package com.example.organizafinancas.domain.model

import com.example.organizafinancas.domain.enums.PaymentTypeEnum
import java.time.LocalDate

sealed class PaymentTypeFilter(
    type: PaymentTypeEnum,
    isSelected: Boolean,
    var initialDate: LocalDate,
    var finishDate: LocalDate,
): SelectableFilter(type.paymentType, isSelected){

    class CreditFilter(
        isSelected: Boolean = true,
        initialDate: LocalDate = LocalDate.of(2024, 4, 24),
        finishDate: LocalDate = LocalDate.of(2024, 5, 23)
    ): PaymentTypeFilter(PaymentTypeEnum.CREDIT, isSelected, initialDate, finishDate)

    class CashFilter(
        isSelected: Boolean = true,
        initialDate: LocalDate = LocalDate.of(2024, 4, 27),
        finishDate: LocalDate = LocalDate.of(2024, 5, 26)
    ): PaymentTypeFilter(PaymentTypeEnum.CASH, isSelected, initialDate, finishDate)
}