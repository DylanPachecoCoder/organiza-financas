package com.example.organizafinancas.domain.usecase

import com.example.organizafinancas.data.repository.CategoryRepository
import com.example.organizafinancas.data.repository.PaymentTypeRepository
import com.example.organizafinancas.domain.model.Category
import com.example.organizafinancas.domain.model.Filter
import com.example.organizafinancas.domain.model.PaymentType
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFiltersUseCase @Inject constructor(
    private val paymentTypeRepository: PaymentTypeRepository,
    private val categoryRepository: CategoryRepository,
) {

    suspend operator fun invoke() = flow {
        val filters = mutableListOf<Filter>()
        paymentTypeRepository.fetchPaymentTypeFilters().collect { newPaymentTypes ->
            val oldPaymentTypes = filters.filterIsInstance<PaymentType>()
            filters.removeAll(oldPaymentTypes)
            filters.addAll(newPaymentTypes)
            emit(filters)
        }
        categoryRepository.fetchCategoryFilters().collect { newCategories ->
            val oldCategories = filters.filterIsInstance<Category>()
            filters.removeAll(oldCategories)
            filters.addAll(newCategories)
            emit(filters)
        }
    }
}