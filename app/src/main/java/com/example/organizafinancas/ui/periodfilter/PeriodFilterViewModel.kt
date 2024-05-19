package com.example.organizafinancas.ui.periodfilter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.organizafinancas.commons.extensions.toLocalDate
import com.example.organizafinancas.data.Repository
import com.example.organizafinancas.domain.model.PaymentTypeFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeriodFilterViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _filterList = MutableLiveData<MutableList<PaymentTypeFilter>>()
    val filterList: LiveData<MutableList<PaymentTypeFilter>> = _filterList

    init {
        fetchFilterList()
    }

    private fun fetchFilterList() {
        viewModelScope.launch {
            _filterList.value = repository.fetchPaymentFilters()
        }
    }

    fun changeDate(initialDate: Long, finishDate: Long, paymentFilter: PaymentTypeFilter) {
        paymentFilter.initialDate = initialDate.toLocalDate()
        paymentFilter.finishDate = finishDate.toLocalDate()
    }
}