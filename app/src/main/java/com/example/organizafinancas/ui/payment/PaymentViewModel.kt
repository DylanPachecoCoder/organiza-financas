package com.example.organizafinancas.ui.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.organizafinancas.commons.extensions.EMPTY
import com.example.organizafinancas.commons.extensions.ZERO
import com.example.organizafinancas.commons.extensions.toCurrency
import com.example.organizafinancas.domain.model.Payment
import com.example.organizafinancas.domain.model.Filter
import com.example.organizafinancas.domain.usecase.GetFiltersUseCase
import com.example.organizafinancas.domain.usecase.GetPaymentsByFiltersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val getFiltersUseCase: GetFiltersUseCase,
    private val getPaymentsByFiltersUseCase: GetPaymentsByFiltersUseCase,
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