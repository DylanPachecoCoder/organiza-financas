package com.example.organizafinancas.data.source.local

import com.example.organizafinancas.domain.enums.PaymentTypeEnum
import com.example.organizafinancas.domain.model.Filter
import com.example.organizafinancas.domain.model.Payment
import com.example.organizafinancas.domain.model.PaymentTypeFilter
import java.time.LocalDate
import javax.inject.Inject

class PaymentDaoImpl @Inject constructor() : PaymentDao {

    override fun getAll() = paymentList

    override fun getByPaymentType(paymentTypeList: List<PaymentTypeFilter>) =
        paymentList.filter { isInPaymentFilter(it, paymentTypeList) }


    private fun isInPaymentFilter(
        payment: Payment,
        paymentFilterList: List<PaymentTypeFilter>
    ) =
        paymentFilterList.any { filter ->
            filter.name == payment.type.paymentType
                    && filter.initialDate <= payment.date
                    && filter.finishDate >= payment.date
        }

    private val paymentList =
        mutableListOf(
            Payment(
                name = "teste 1",
                category = Filter("restaurante"),
                type = PaymentTypeEnum.CREDIT,
                date = LocalDate.of(2024, 5, 28)
            ),
            Payment(
                name = "teste 2",
                category = Filter("carro"),
                type = PaymentTypeEnum.CASH,
                date = LocalDate.of(2024, 5, 9)
            ),
            Payment(
                name = "teste 3",
                category = Filter("restaurante"),
                type = PaymentTypeEnum.CREDIT,
                date = LocalDate.of(2024, 5, 8)
            ),
            Payment(
                type = PaymentTypeEnum.CASH,
                category = Filter("mercado"),
            ),
            Payment(
                type = PaymentTypeEnum.CREDIT,
                category = Filter("comida"),
            ),
            Payment(
                type = PaymentTypeEnum.CREDIT,
                category = Filter("sair"),
            ),
            Payment(type = PaymentTypeEnum.CASH),
            Payment(type = PaymentTypeEnum.CASH),
            Payment(type = PaymentTypeEnum.CREDIT),
        )

}