package com.example.organizafinancas.domain.usecase

import com.example.organizafinancas.data.repository.PaymentRepository
import com.example.organizafinancas.domain.model.Category
import com.example.organizafinancas.domain.model.Payment
import com.example.organizafinancas.domain.model.PaymentType
import com.example.organizafinancas.domain.model.Filter
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPaymentsByFiltersUseCase @Inject constructor(
    private val repository: PaymentRepository
) {

    operator fun invoke(filterList: List<Filter>) = flow {
        val paymentTypeFilters = getPaymentTypeFiltersSelected(filterList)
        val filteredPayments = mutableListOf<Payment>()
        repository.getByPaymentType(paymentTypeFilters).collect { payments ->
            filteredPayments.addAll(filterPaymentsByCategory(payments, filterList))
        }
        emit(filteredPayments)
    }

    private fun filterPaymentsByCategory(
        payments: List<Payment>,
        selectedFilters: List<Filter>
    ): List<Payment> {
        val categories = getCategoryFiltersSelected(selectedFilters)
        return payments.filter { payment ->
            categories.any { filter ->
                filter.name == payment.category.name
            }
        }
    }

    private fun getCategoryFiltersSelected(filterList: List<Filter>) =
        filterList.filterIsInstance<Category>().filter { it.isSelected }

    private fun getPaymentTypeFiltersSelected(filterList: List<Filter>) =
        filterList.filterIsInstance<PaymentType>().filter { it.isSelected }
}