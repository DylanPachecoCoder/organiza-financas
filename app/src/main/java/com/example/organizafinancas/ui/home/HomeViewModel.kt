package com.example.organizafinancas.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.organizafinancas.commons.extensions.ZERO
import com.example.organizafinancas.data.Repository
import com.example.organizafinancas.domain.model.Payment
import com.example.organizafinancas.domain.model.PaymentFilter
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: Repository = Repository.getInstance()
) : ViewModel() {

    private val _filterList = MutableLiveData<MutableList<PaymentFilter>>()
    val filterList: LiveData<MutableList<PaymentFilter>> = _filterList

    private val _filteredPaymentList = MutableLiveData<MutableList<Payment>?>()
    val filteredPaymentList: LiveData<MutableList<Payment>?> = _filteredPaymentList

    fun fetchFilterList() {
        viewModelScope.launch {
            _filterList.value = repository.fetchFilters()
        }
    }

    fun fetchPaymentList() {
        viewModelScope.launch {
            _filteredPaymentList.value = repository.fetchPayments()
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