package com.example.organizafinancas.domain.usecase

import com.example.organizafinancas.data.repository.CategoryRepository
import com.example.organizafinancas.data.repository.PaymentTypeRepository
import com.example.organizafinancas.domain.model.Filter
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFiltersUseCase @Inject constructor(
    private val paymentTypeRepository: PaymentTypeRepository,
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

    private suspend fun getPaymentTypes() = paymentTypeRepository.fetchPaymentTypes()

    private suspend fun getCategories() = categoryRepository.fetchCategoryFilters()

}