package com.example.organizafinancas.ui.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.organizafinancas.commons.di.provideDefaultCategory
import com.example.organizafinancas.commons.extensions.EMPTY
import com.example.organizafinancas.commons.extensions.ZERO
import com.example.organizafinancas.commons.extensions.toCurrency
import com.example.organizafinancas.data.repository.CategoryRepository
import com.example.organizafinancas.data.repository.PaymentRepository
import com.example.organizafinancas.data.repository.PaymentTypeRepository
import com.example.organizafinancas.domain.enums.PaymentTypeEnum
import com.example.organizafinancas.domain.model.Category
import com.example.organizafinancas.domain.model.Payment
import com.example.organizafinancas.domain.model.Filter
import com.example.organizafinancas.domain.model.PaymentType
import com.example.organizafinancas.domain.usecase.GetFiltersUseCase
import com.example.organizafinancas.domain.usecase.GetPaymentsByFiltersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val getFiltersUseCase: GetFiltersUseCase,
    private val getPaymentsByFiltersUseCase: GetPaymentsByFiltersUseCase,
    private val paymentTypeRepository: PaymentTypeRepository,
    private val categoryRepository: CategoryRepository,
    private val paymentRepository: PaymentRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        refreshData()
    }

    private fun refreshData() {
        viewModelScope.launch {
            getFiltersUseCase().collect {
                _uiState.update { currentValue -> currentValue.copy(filters = it) }
                fetchPayments()
            }
        }
    }

    fun fetchPayments() {
        viewModelScope.launch {
            getPaymentsByFiltersUseCase(uiState.value.filters).collect {
                _uiState.update { currentValue ->
                    currentValue.copy(
                        payments = it,
                        total = sumValues(it)
                    )
                }
            }
        }
    }

    fun updateFilter(filter: Filter, isChecked: Boolean) {
        viewModelScope.launch {
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

    fun insertPayment(){
        viewModelScope.launch {
            paymentRepository.insert(Payment(
                name = "teste 1",
                category = provideDefaultCategory().name,
                paymentMethod = PaymentTypeEnum.CREDIT.paymentType,
                date = LocalDate.of(2024, 5, 28),
                value = 10.0
            ))
            paymentRepository.insert(
                Payment(
                    name = "teste 2",
                    category = provideDefaultCategory().name,
                    paymentMethod = PaymentTypeEnum.CASH.paymentType,
                    date = LocalDate.of(2024, 5, 9),
                    value = 10.0
                )
            )
        }
    }

    fun insertPaymentMethod(){
        viewModelScope.launch {
            paymentTypeRepository.savePaymentType(
                PaymentType(
                name = PaymentTypeEnum.CASH.paymentType,
                isSelected = true,
                initialDate = LocalDate.of(2024, 5, 1),
                finishDate = LocalDate.of(2024, 6, 30)
            ))
            paymentTypeRepository.savePaymentType(
                PaymentType(
                    name = PaymentTypeEnum.CREDIT.paymentType,
                    isSelected = true,
                    initialDate = LocalDate.of(2024, 5, 1),
                    finishDate = LocalDate.of(2024, 6, 30)
                )
            )
        }
    }

    private fun sumValues(payments: MutableList<Payment>): String {
        var sum = Double.ZERO
        payments.forEach {
            sum += it.value
        }
        return sum.toCurrency()
    }
}

data class UiState(
    val filters: List<Filter> = emptyList(),
    val payments: List<Payment> = emptyList(),
    val total: String = String.EMPTY
)