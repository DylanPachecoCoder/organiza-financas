package com.example.organizafinancas.data.source.local

import com.example.organizafinancas.domain.enums.PaymentTypeEnum
import com.example.organizafinancas.domain.model.Filter
import com.example.organizafinancas.domain.model.Payment
import com.example.organizafinancas.domain.model.PaymentTypeFilter
import com.example.organizafinancas.domain.model.SelectableFilter
import java.time.LocalDate
import javax.inject.Inject

class PaymentDaoImpl @Inject constructor() : PaymentDao {
    override fun getPayments(
        paymentFilterList: List<PaymentTypeFilter>,
        categoryFilterList: List<SelectableFilter>
    ) =
        mutableListOf<Payment>().apply {
            addAll(filterPayments(paymentList, paymentFilterList, categoryFilterList))
            sortDescending()
        }

    private fun filterPayments(
        paymentList: MutableList<Payment>?,
        paymentFilterList: List<PaymentTypeFilter>,
        categoryFilterList: List<SelectableFilter>
    ) =
        paymentList?.filter { payment ->
            isInPaymentFilter(payment, paymentFilterList) &&
                    isInCategoryFilter(payment, categoryFilterList)
        }.orEmpty().toMutableList()

    private fun isInCategoryFilter(
        payment: Payment,
        categoryFilterList: List<SelectableFilter>
    ) =
        categoryFilterList.any { filter ->
            filter.name == payment.category.name
                    && filter.isSelected
        }

    private fun isInPaymentFilter(
        payment: Payment,
        paymentFilterList: List<PaymentTypeFilter>
    ) =
        paymentFilterList.any { filter ->
            filter.name == payment.type.paymentType
                    && filter.isSelected
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