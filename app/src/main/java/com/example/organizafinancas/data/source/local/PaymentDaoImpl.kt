package com.example.organizafinancas.data.source.local

import com.example.organizafinancas.commons.di.provideDefaultCategory
import com.example.organizafinancas.domain.enums.PaymentTypeEnum
import com.example.organizafinancas.domain.model.Payment
import com.example.organizafinancas.domain.model.PaymentType
import java.time.LocalDate
import javax.inject.Inject

class PaymentDaoImpl @Inject constructor() : PaymentDao {

    override fun getAll() = paymentList

    override fun getByPaymentType(paymentTypeList: List<PaymentType>) =
        paymentList.filter { isInPaymentFilter(it, paymentTypeList) }


    private fun isInPaymentFilter(
        payment: Payment,
        paymentFilterList: List<PaymentType>
    ) =
        paymentFilterList.any { filter ->
            filter.name == payment.paymentMethod
                    && filter.initialDate <= payment.date
                    && filter.finishDate >= payment.date
        }

    private val paymentList =
        mutableListOf(
            Payment(
                name = "teste 1",
                category = provideDefaultCategory().name,
                paymentMethod = PaymentTypeEnum.CREDIT.paymentType,
                date = LocalDate.of(2024, 5, 28),
                value = 10.0
            ),
            Payment(
                name = "teste 2",
                category = provideDefaultCategory().name,
                paymentMethod = PaymentTypeEnum.CASH.paymentType,
                date = LocalDate.of(2024, 5, 9),
                value = 10.0
            )
        )
}