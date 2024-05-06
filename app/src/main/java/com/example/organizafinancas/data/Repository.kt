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
        paymentList.sortDescending()
        return paymentList
    }

    fun updateFilter(oldFilter: PaymentFilter, newFilter: PaymentFilter) {
        filterList.remove(oldFilter)
        filterList.add(newFilter)
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
                LocalDate.of(2024, 5, 23)
            ),
            PaymentFilter(
                PaymentTypeEnum.DEBIT,
                LocalDate.of(2024, 4, 27),
                LocalDate.of(2024, 5, 26)
            ),
            PaymentFilter(
                PaymentTypeEnum.CASH,
                LocalDate.of(2024, 4, 27),
                LocalDate.of(2024, 5, 26)
            ),
            PaymentFilter(
                PaymentTypeEnum.PIX,
                LocalDate.of(2024, 4, 27),
                LocalDate.of(2024, 5, 26)
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