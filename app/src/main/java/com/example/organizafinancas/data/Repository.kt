package com.example.organizafinancas.data

import com.example.organizafinancas.domain.enums.PaymentTypeEnum
import com.example.organizafinancas.domain.model.Payment
import com.example.organizafinancas.domain.model.PaymentFilter
import java.time.LocalDate

class Repository private constructor() {

    fun fetchFilters(): MutableList<PaymentFilter> {
        filterList.sort()
       return filterList
    }

    fun fetchPayments(): MutableList<Payment> {
        val filteredPaymentList = applyFilters(paymentList).toMutableList()
        filteredPaymentList.sortDescending()
        return filteredPaymentList
    }

    private fun applyFilters(paymentList: MutableList<Payment>?) =
        paymentList?.filter { payment ->
            filterList.any { filter ->
                filter.type == payment.type
                        && filter.isSelected
                        && filter.initialDate <= payment.date
                        && filter.finishDate >= payment.date
            }
        }.orEmpty()

    private val paymentList =
        mutableListOf(
            Payment(
                name = "teste 1",
                type = PaymentTypeEnum.CREDIT,
                date = LocalDate.of(2024, 5, 28)
            ),
            Payment(
                name = "teste 2",
                type = PaymentTypeEnum.DEBIT,
                date = LocalDate.of(2024, 5, 9)
            ),
            Payment(
                name = "teste 3",
                type = PaymentTypeEnum.PIX,
                date = LocalDate.of(2024, 5, 8)
            ),
            Payment(type = PaymentTypeEnum.CASH),
            Payment(type = PaymentTypeEnum.CREDIT),
            Payment(type = PaymentTypeEnum.CREDIT),
            Payment(type = PaymentTypeEnum.DEBIT),
            Payment(type = PaymentTypeEnum.DEBIT),
            Payment(type = PaymentTypeEnum.PIX),
        )

    private val filterList =
        mutableListOf(
            PaymentFilter(
                PaymentTypeEnum.CREDIT,
                LocalDate.of(2024, 4, 24),
                LocalDate.of(2024, 5, 23),
                true
            ),
            PaymentFilter(
                PaymentTypeEnum.DEBIT,
                LocalDate.of(2024, 4, 27),
                LocalDate.of(2024, 5, 26),
                true
            ),
            PaymentFilter(
                PaymentTypeEnum.CASH,
                LocalDate.of(2024, 4, 27),
                LocalDate.of(2024, 5, 26),
                true
            ),
            PaymentFilter(
                PaymentTypeEnum.PIX,
                LocalDate.of(2024, 4, 27),
                LocalDate.of(2024, 5, 26),
                true
            ),
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