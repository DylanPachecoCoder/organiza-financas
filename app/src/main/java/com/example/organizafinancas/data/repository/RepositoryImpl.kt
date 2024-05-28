package com.example.organizafinancas.data.repository

import com.example.organizafinancas.domain.enums.PaymentTypeEnum
import com.example.organizafinancas.domain.model.Filter
import com.example.organizafinancas.domain.model.Payment
import com.example.organizafinancas.domain.model.PaymentTypeFilter
import com.example.organizafinancas.domain.model.SelectableFilter
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor() : Repository {

    override fun fetchFilters() = flow {
        val selectableFilters = mutableListOf<SelectableFilter>().apply {
            addAll(paymentFilterList)
            addAll(categoryFilterList)
        }
        emit(selectableFilters)
    }

    override fun fetchPayments() = flow {
        val payments = mutableListOf<Payment>().apply {
            addAll(filterPayments(paymentList))
            sortDescending()
        }
        emit(payments)
    }

    override fun fetchPaymentFilters() = flow {
        emit(paymentFilterList)
    }

    override fun fetchCategoryFilters() = flow {
        emit(categoryFilterList)
    }

    override fun saveCategory(category: SelectableFilter?): MutableList<SelectableFilter> {
        category?.also { categoryFilterList.add(category) }
        return categoryFilterList
    }

    override fun deleteCategory(category: SelectableFilter?): MutableList<SelectableFilter> {
        category?.also { categoryFilterList.remove(category) }
        return categoryFilterList
    }

    override fun updateCategory(category: SelectableFilter?): MutableList<SelectableFilter> {
        category?.also {
            categoryFilterList.forEach {
                if (it.hashCode() == category.hashCode()) {
                    categoryFilterList.remove(it)
                    categoryFilterList.add(category)
                }
            }
        }
        return categoryFilterList
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

    private val paymentFilterList =
        mutableListOf(
            PaymentTypeFilter.CreditFilter(),
            PaymentTypeFilter.CashFilter(),
        )

    private val categoryFilterList =
        mutableListOf(
            SelectableFilter("restaurante"),
            SelectableFilter("sem categoria"),
            SelectableFilter("carro"),
            SelectableFilter("mercado"),
            SelectableFilter("comida"),
            SelectableFilter("sair"),
        )
}