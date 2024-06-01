package com.example.organizafinancas.data.source.local

import com.example.organizafinancas.domain.enums.PaymentTypeEnum
import com.example.organizafinancas.domain.model.Category
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
            filter.name == payment.type.paymentType
                    && filter.initialDate <= payment.date
                    && filter.finishDate >= payment.date
        }

    private val paymentList =
        mutableListOf(
            Payment(
                name = "teste 1",
                category = Category("restaurante"),
                type = PaymentTypeEnum.CREDIT,
                date = LocalDate.of(2024, 5, 28)
            ),
            Payment(
                name = "teste 2",
                category = Category("carro"),
                type = PaymentTypeEnum.CASH,
                date = LocalDate.of(2024, 5, 9)
            ),
            Payment(
                name = "teste 3",
                category = Category("restaurante"),
                type = PaymentTypeEnum.CREDIT,
                date = LocalDate.of(2024, 5, 8)
            ),
            Payment(
                type = PaymentTypeEnum.CASH,
                category = Category("mercado"),
            ),
            Payment(
                type = PaymentTypeEnum.CREDIT,
                category = Category("comida"),
            ),
            Payment(
                type = PaymentTypeEnum.CREDIT,
                category = Category("sair"),
            ),
            Payment(type = PaymentTypeEnum.CASH),
            Payment(type = PaymentTypeEnum.CASH),
            Payment(type = PaymentTypeEnum.CREDIT),
        )

}