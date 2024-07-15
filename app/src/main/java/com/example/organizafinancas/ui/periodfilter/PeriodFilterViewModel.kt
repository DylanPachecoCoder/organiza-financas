package com.example.organizafinancas.ui.periodfilter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.organizafinancas.commons.extensions.toLocalDate
import com.example.organizafinancas.data.repository.PaymentMethodRepository
import com.example.organizafinancas.domain.model.PaymentMethod
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeriodFilterViewModel @Inject constructor(
    private val repository: PaymentMethodRepository
) : ViewModel() {

    private val _filterList = MutableLiveData<List<PaymentMethod>>()
    val filterList: LiveData<List<PaymentMethod>> = _filterList

    init {
        fetchFilterList()
    }

    private fun fetchFilterList() {
        viewModelScope.launch {
            repository.fetchPaymentTypes().collect{
                _filterList.value = it
            }
        }
    }

    fun changeDate(initialDate: Long, finishDate: Long, paymentFilter: PaymentMethod) {
        viewModelScope.launch {
            repository.savePaymentType(
                paymentFilter.copy(
                    initialDate = initialDate.toLocalDate(),
                    finishDate = finishDate.toLocalDate()
                )
            )
        }
    }
}