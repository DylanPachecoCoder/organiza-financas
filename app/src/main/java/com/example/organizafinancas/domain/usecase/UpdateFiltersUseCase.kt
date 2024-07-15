package com.example.organizafinancas.domain.usecase

import com.example.organizafinancas.data.repository.CategoryRepository
import com.example.organizafinancas.data.repository.PaymentMethodRepository
import com.example.organizafinancas.domain.model.Category
import com.example.organizafinancas.domain.model.Filter
import com.example.organizafinancas.domain.model.PaymentMethod
import javax.inject.Inject

class UpdateFiltersUseCase @Inject constructor(
    private val paymentMethodRepository: PaymentMethodRepository,
    private val categoryRepository: CategoryRepository,
) {

    suspend operator fun invoke(filter: Filter, isChecked: Boolean) {
        when(filter){
            is PaymentMethod -> {
                val copy = filter.copy(isSelected = isChecked)
                paymentMethodRepository.savePaymentType(copy)
            }
            is Category -> {
                val copy = filter.copy(isSelected = isChecked)
                categoryRepository.saveCategory(copy)
            }
        }
    }
}