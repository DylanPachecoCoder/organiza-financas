package com.example.organizafinancas.data

import com.example.organizafinancas.domain.enums.PaymentTypeEnum
import com.example.organizafinancas.domain.model.Payment
import com.example.organizafinancas.domain.model.PaymentTypeFilter
import com.example.organizafinancas.domain.model.SelectableFilter
import java.time.LocalDate

class Repository private constructor() {

    fun fetchFilters() = mutableListOf<SelectableFilter>().apply {
        addAll(paymentFilterList)
        addAll(categoryFilterList)
    }

    fun fetchPaymentFilters() = paymentFilterList

    fun fetchPayments() = mutableListOf<Payment>().apply {
        addAll(filterPayments(paymentList))
        sortDescending()
    }

    private fun filterPayments(paymentList: MutableList<Payment>?) =
        paymentList?.filter { payment ->
            isInPaymentFilter(payment) && isInCategoryFilter(payment)
        }.orEmpty().toMutableList()

    private fun isInCategoryFilter(payment: Payment) =
        categoryFilterList.any { filter ->
            filter.name == payment.category.name
                    && filter.isSelected
        }

    private fun isInPaymentFilter(payment: Payment) =
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
                type = PaymentTypeEnum.CREDIT,
                date = LocalDate.of(2024, 5, 28)
            ),
            Payment(
                name = "teste 2",
                type = PaymentTypeEnum.CASH,
                date = LocalDate.of(2024, 5, 9)
            ),
            Payment(
                name = "teste 3",
                type = PaymentTypeEnum.CREDIT,
                date = LocalDate.of(2024, 5, 8)
            ),
            Payment(type = PaymentTypeEnum.CASH),
            Payment(type = PaymentTypeEnum.CREDIT),
            Payment(type = PaymentTypeEnum.CREDIT),
            Payment(type = PaymentTypeEnum.CASH),
            Payment(type = PaymentTypeEnum.CASH),
            Payment(type = PaymentTypeEnum.CREDIT),
        )

    private val paymentFilterList =
        mutableListOf(
            PaymentTypeFilter.CreditFilter(),
            PaymentTypeFilter.CashFilter(),
        )

    private val categoryFilterList =
        mutableListOf(
            SelectableFilter("restaurante"),
            SelectableFilter("sem categoria"),
        )

    companion object {

        @Volatile
        private var instance: Repository? = null

        fun getInstance() =
            instance ?: synchronized(this) { // synchronized to avoid concurrency problem
                instance ?: Repository().also { instance = it }
            }
    }
}