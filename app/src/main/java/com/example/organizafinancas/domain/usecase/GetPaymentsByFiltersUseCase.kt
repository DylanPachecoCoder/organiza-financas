package com.example.organizafinancas.domain.usecase

import com.example.organizafinancas.data.repository.PaymentRepository
import com.example.organizafinancas.domain.model.Category
import com.example.organizafinancas.domain.model.PaymentType
import com.example.organizafinancas.domain.model.Filter
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPaymentsByFiltersUseCase @Inject constructor(
    private val repository: PaymentRepository
) {

    operator fun invoke(filterList: List<Filter>) = flow {
        val paymentTypeFilters = getPaymentTypeFiltersSelected(filterList)
        val categoriesFilters = getCategoryFiltersSelected(filterList)
        repository.getByPaymentType(paymentTypeFilters, categoriesFilters).collect { payments ->
            emit(payments.toMutableList())
        }
    }

    private fun getCategoryFiltersSelected(filterList: List<Filter>) =
        filterList.filterIsInstance<Category>().filter { it.isSelected }

    private fun getPaymentTypeFiltersSelected(filterList: List<Filter>) =
        filterList.filterIsInstance<PaymentType>().filter { it.isSelected }
}