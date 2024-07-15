package com.example.organizafinancas.ui.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.organizafinancas.commons.extensions.EMPTY
import com.example.organizafinancas.commons.extensions.ZERO
import com.example.organizafinancas.commons.extensions.toCurrency
import com.example.organizafinancas.data.repository.PaymentRepository
import com.example.organizafinancas.domain.model.Filter
import com.example.organizafinancas.domain.model.Payment
import com.example.organizafinancas.domain.model.PaymentWithCategoryAndPaymentMethod
import com.example.organizafinancas.domain.usecase.GetFiltersUseCase
import com.example.organizafinancas.domain.usecase.UpdateFiltersUseCase
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
    private val updateFiltersUseCase: UpdateFiltersUseCase,
    private val paymentRepository: PaymentRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        refreshData()
    }

    private fun refreshData() {
        fetchFilters()
        fetchPayments()
    }

    private fun fetchFilters() {
        viewModelScope.launch {
            getFiltersUseCase().collect {
                _uiState.update { currentValue -> currentValue.copy(filters = it) }
            }
        }
    }

    fun fetchPayments() {
        viewModelScope.launch {
            paymentRepository.getByPaymentType().collect {
                _uiState.update { currentValue ->
                    currentValue.copy(payments = it, total = sumValues(it))
                }
            }
        }
    }

    fun updateFilter(filter: Filter, isChecked: Boolean) {
        viewModelScope.launch {
            updateFiltersUseCase(filter, isChecked)
        }
    }

    fun insertPayment() {
        viewModelScope.launch {
            paymentRepository.insert(
                Payment(
                    name = "teste 1",
                    categoryId = 1,
                    paymentMethodId = 1,
                    date = LocalDate.of(2024, 5, 28),
                    value = 10.0
                )
            )
            paymentRepository.insert(
                Payment(
                    name = "teste 2",
                    categoryId = 1,
                    paymentMethodId = 2,
                    date = LocalDate.of(2024, 5, 9),
                    value = 10.0
                )
            )
            refreshData()
        }
    }

    private fun sumValues(payments: List<PaymentWithCategoryAndPaymentMethod>): String {
        var sum = Double.ZERO
        payments.forEach {
            sum += it.payment.value
        }
        return sum.toCurrency()
    }
}

data class UiState(
    val filters: List<Filter> = emptyList(),
    val payments: List<PaymentWithCategoryAndPaymentMethod> = emptyList(),
    val total: String = String.EMPTY
)