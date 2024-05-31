package com.example.organizafinancas.ui.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.organizafinancas.commons.extensions.ZERO
import com.example.organizafinancas.domain.model.Payment
import com.example.organizafinancas.domain.model.SelectableFilter
import com.example.organizafinancas.domain.usecase.GetFiltersUseCase
import com.example.organizafinancas.domain.usecase.GetPaymentsByFiltersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val getFiltersUseCase: GetFiltersUseCase,
    private val getPaymentsByFiltersUseCase: GetPaymentsByFiltersUseCase,
) : ViewModel() {

    private val _filterList = MutableLiveData<MutableList<SelectableFilter>>()
    val filterList: LiveData<MutableList<SelectableFilter>> = _filterList

    private val _filteredPaymentList = MutableLiveData<List<Payment>>()
    val filteredPaymentList: LiveData<List<Payment>> = _filteredPaymentList

    init {
        fetchFilterList()
    }

    private fun fetchFilterList() {
        viewModelScope.launch {
            getFiltersUseCase().collect{
                _filterList.value = it
                fetchPaymentList()
            }
        }
    }

    fun fetchPaymentList() {
        viewModelScope.launch {
            getPaymentsByFiltersUseCase(_filterList.value.orEmpty()).collect{
                _filteredPaymentList.value = it
            }
        }
    }

    fun sumValues(): Double {
        var sum = Double.ZERO
        _filteredPaymentList.value?.forEach {
            sum += it.value
        }
        return sum
    }
}