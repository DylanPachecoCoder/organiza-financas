package com.example.organizafinancas.ui.periodfilter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.organizafinancas.commons.extensions.toLocalDate
import com.example.organizafinancas.data.Repository
import com.example.organizafinancas.domain.model.PaymentTypeFilter
import kotlinx.coroutines.launch

class PeriodFilterViewModel(
    private val repository: Repository = Repository.getInstance()
) : ViewModel() {

    private val _filterList = MutableLiveData<MutableList<PaymentTypeFilter>>()
    val filterList: LiveData<MutableList<PaymentTypeFilter>> = _filterList

    fun fetchFilterList() {
        viewModelScope.launch {
            _filterList.value = repository.fetchPaymentFilters()
        }
    }

    fun changeDate(initialDate: Long, finishDate: Long, paymentFilter: PaymentTypeFilter) {
        paymentFilter.initialDate = initialDate.toLocalDate()
        paymentFilter.finishDate = finishDate.toLocalDate()
    }
}