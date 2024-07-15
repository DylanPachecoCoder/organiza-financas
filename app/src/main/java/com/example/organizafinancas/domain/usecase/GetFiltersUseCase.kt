package com.example.organizafinancas.domain.usecase

import com.example.organizafinancas.data.repository.CategoryRepository
import com.example.organizafinancas.data.repository.PaymentMethodRepository
import com.example.organizafinancas.domain.model.Filter
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFiltersUseCase @Inject constructor(
    private val paymentMethodRepository: PaymentMethodRepository,
    private val categoryRepository: CategoryRepository,
) {

    suspend operator fun invoke() = flow {
        combine(getPaymentTypes(), getCategories()) { paymentTypes, categories ->
            val filters = mutableListOf<Filter>()
            filters.addAll(paymentTypes)
            filters.addAll(categories)
            filters
        }.collect { emit(it) }
    }

    private suspend fun getPaymentTypes() = paymentMethodRepository.fetchPaymentTypes()

    private suspend fun getCategories() = categoryRepository.fetchCategoryFilters()

}