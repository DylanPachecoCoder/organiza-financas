package com.example.organizafinancas.ui.periodfilter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.organizafinancas.commons.extensions.toLocalDate
import com.example.organizafinancas.data.repository.PaymentRepository
import com.example.organizafinancas.domain.model.PaymentTypeFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeriodFilterViewModel @Inject constructor(private val paymentRepository: PaymentRepository) : ViewModel() {

    private val _filterList = MutableLiveData<List<PaymentTypeFilter>>()
    val filterList: LiveData<List<PaymentTypeFilter>> = _filterList

    init {
        fetchFilterList()
    }

    private fun fetchFilterList() {
        viewModelScope.launch {
            paymentRepository.fetchPaymentFilters().collect{
                _filterList.value = it
            }
        }
    }

    fun changeDate(initialDate: Long, finishDate: Long, paymentFilter: PaymentTypeFilter) {
        paymentFilter.initialDate = initialDate.toLocalDate()
        paymentFilter.finishDate = finishDate.toLocalDate()
    }
}