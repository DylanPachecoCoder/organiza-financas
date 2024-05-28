package com.example.organizafinancas.ui.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.organizafinancas.commons.extensions.ZERO
import com.example.organizafinancas.data.repository.Repository
import com.example.organizafinancas.domain.model.Payment
import com.example.organizafinancas.domain.model.SelectableFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _filterList = MutableLiveData<MutableList<SelectableFilter>>()
    val filterList: LiveData<MutableList<SelectableFilter>> = _filterList

    private val _filteredPaymentList = MutableLiveData<MutableList<Payment>?>()
    val filteredPaymentList: LiveData<MutableList<Payment>?> = _filteredPaymentList

    init {
        fetchFilterList()
        fetchPaymentList()
    }

    private fun fetchFilterList() {
        viewModelScope.launch {
            repository.fetchFilters().collect{
                _filterList.value = it
            }
        }
    }

    fun fetchPaymentList() {
        viewModelScope.launch {
            repository.fetchPayments().collect{
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