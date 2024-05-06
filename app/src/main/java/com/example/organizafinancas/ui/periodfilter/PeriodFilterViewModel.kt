package com.example.organizafinancas.ui.periodfilter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.organizafinancas.commons.extensions.toLocalDate
import com.example.organizafinancas.data.Repository
import com.example.organizafinancas.domain.model.PaymentFilter
import kotlinx.coroutines.launch

class PeriodFilterViewModel(
    private val repository: Repository = Repository.getInstance()
) : ViewModel() {

    private val _filterList = MutableLiveData<MutableList<PaymentFilter>>()
    val filterList: LiveData<MutableList<PaymentFilter>> = _filterList

    fun fetchFilterList() {
        viewModelScope.launch {
            _filterList.value = repository.fetchFilters()
        }
    }

    fun changeDate(initialDate: Long, finishDate: Long, paymentFilter: PaymentFilter) {
        paymentFilter.initialDate = initialDate.toLocalDate()
        paymentFilter.finishDate = finishDate.toLocalDate()
    }
}