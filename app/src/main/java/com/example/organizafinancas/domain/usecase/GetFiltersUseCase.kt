package com.example.organizafinancas.domain.usecase

import com.example.organizafinancas.data.repository.CategoryRepository
import com.example.organizafinancas.data.repository.PaymentTypeRepository
import com.example.organizafinancas.domain.model.Filter
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFiltersUseCase @Inject constructor(
    private val paymentTypeRepository: PaymentTypeRepository,
    private val categoryRepository: CategoryRepository,
) {

    operator fun invoke() = flow {
        val filters = mutableListOf<Filter>().apply {
            paymentTypeRepository.fetchPaymentTypeFilters().collect { addAll(it) }
            categoryRepository.fetchCategoryFilters().collect { addAll(it) }
        }
        emit(filters)
    }
}