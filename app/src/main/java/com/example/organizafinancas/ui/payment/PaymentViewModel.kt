package com.example.organizafinancas.ui.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.organizafinancas.commons.extensions.EMPTY
import com.example.organizafinancas.commons.extensions.ZERO
import com.example.organizafinancas.commons.extensions.toCurrency
import com.example.organizafinancas.domain.model.Payment
import com.example.organizafinancas.domain.model.SelectableFilter
import com.example.organizafinancas.domain.usecase.GetFiltersUseCase
import com.example.organizafinancas.domain.usecase.GetPaymentsByFiltersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val getFiltersUseCase: GetFiltersUseCase,
    private val getPaymentsByFiltersUseCase: GetPaymentsByFiltersUseCase,
) : ViewModel() {

    private val _filteredPaymentList = MutableLiveData<List<Payment>>()
    val filteredPaymentList: LiveData<List<Payment>> = _filteredPaymentList

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchFilterList()
    }

    private fun fetchFilterList() {
        viewModelScope.launch {
            getFiltersUseCase().collect {
                _uiState.update { currentValue -> currentValue.copy(filters = it) }
                fetchPaymentList()
            }
        }
    }

    fun fetchPaymentList() {
        viewModelScope.launch {
            getPaymentsByFiltersUseCase(uiState.value.filters).collect {
                _filteredPaymentList.value = it
                _uiState.update { currentValue ->
                    currentValue.copy(
                        payments = it,
                        total = sumValues()
                    )
                }
            }
        }
    }

    private fun sumValues(): String {
        var sum = Double.ZERO
        _filteredPaymentList.value?.forEach {
            sum += it.value
        }
        return sum.toCurrency()
    }
}

data class UiState(
    val filters: List<SelectableFilter> = emptyList(),
    val payments: List<Payment> = emptyList(),
    val total: String = String.EMPTY
)