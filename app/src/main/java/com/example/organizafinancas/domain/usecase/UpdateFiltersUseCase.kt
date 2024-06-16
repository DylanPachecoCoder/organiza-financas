package com.example.organizafinancas.domain.usecase

import com.example.organizafinancas.data.repository.CategoryRepository
import com.example.organizafinancas.data.repository.PaymentTypeRepository
import com.example.organizafinancas.domain.model.Category
import com.example.organizafinancas.domain.model.Filter
import com.example.organizafinancas.domain.model.PaymentType
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateFiltersUseCase @Inject constructor(
    private val paymentTypeRepository: PaymentTypeRepository,
    private val categoryRepository: CategoryRepository,
) {

    suspend operator fun invoke(filter: Filter, isChecked: Boolean) {
        when(filter){
            is PaymentType -> {
                val copy = filter.copy(isSelected = isChecked)
                paymentTypeRepository.savePaymentType(copy)
            }
            is Category -> {
                val copy = filter.copy(isSelected = isChecked)
                categoryRepository.saveCategory(copy)
            }
        }
    }
}