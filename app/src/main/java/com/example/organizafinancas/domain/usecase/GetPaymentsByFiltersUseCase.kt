package com.example.organizafinancas.domain.usecase

import com.example.organizafinancas.data.repository.PaymentRepository
import com.example.organizafinancas.domain.model.Payment
import com.example.organizafinancas.domain.model.PaymentTypeFilter
import com.example.organizafinancas.domain.model.SelectableFilter
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPaymentsByFiltersUseCase @Inject constructor(
    private val repository: PaymentRepository
) {

    operator fun invoke(filterList: List<SelectableFilter>) = flow {
        val paymentTypeFilters = getPaymentTypeFilters(filterList)
        val filteredPayments = mutableListOf<Payment>()
        repository.getByPaymentType(paymentTypeFilters).collect { payments ->
            filteredPayments.addAll(filterPaymentsByCategory(payments, filterList))
        }
        emit(filteredPayments)
    }

    private fun filterPaymentsByCategory(
        payments: List<Payment>,
        selectedFilters: List<SelectableFilter>
    ) = payments.filter { isInCategoryFilter(it, selectedFilters) }

    private fun getPaymentTypeFilters(filterList: List<SelectableFilter>) =
        filterList.filterIsInstance<PaymentTypeFilter>().filter { it.isSelected }

    private fun isInCategoryFilter(
        payment: Payment,
        categoryFilterList: List<SelectableFilter>
    ) = categoryFilterList.any { filter ->
        filter.name == payment.category.name && filter.isSelected
    }
}