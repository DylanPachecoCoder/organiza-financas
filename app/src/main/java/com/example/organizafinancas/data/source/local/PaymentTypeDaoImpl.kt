package com.example.organizafinancas.data.source.local

import com.example.organizafinancas.domain.enums.PaymentTypeEnum
import com.example.organizafinancas.domain.model.PaymentType
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import javax.inject.Inject

class PaymentTypeDaoImpl @Inject constructor() : PaymentTypeDao {
    override fun getAll() = flow { emit(paymentFilterList) }

    private val paymentFilterList =
        mutableListOf(
            PaymentType(
                name = PaymentTypeEnum.CASH.paymentType,
                isSelected = true,
                initialDate = LocalDate.of(2024, 5, 1),
                finishDate = LocalDate.of(2024, 6, 30)
            ),
            PaymentType(
                name = PaymentTypeEnum.CREDIT.paymentType,
                isSelected = true,
                initialDate = LocalDate.of(2024, 5, 1),
                finishDate = LocalDate.of(2024, 6, 30)
            ),
        )
}