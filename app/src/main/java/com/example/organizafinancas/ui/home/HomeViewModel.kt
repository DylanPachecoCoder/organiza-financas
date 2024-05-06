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

    private var paymentList: List<Payment>? = null

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
            paymentList = repository.fetchPayments()
            setPaymentList(paymentList?.toMutableList())
        }
    }

    fun filterList(option: PaymentFilter, isChecked: Boolean) {
        val selectedPaymentsList = paymentList?.filter { it.type == option.type }.orEmpty()
        val currentPaymentList = _filteredPaymentList.value
        if (isChecked) {
            currentPaymentList?.addAll(selectedPaymentsList)
        } else {
            currentPaymentList?.removeAll(selectedPaymentsList)
        }
        setPaymentList(currentPaymentList)
    }

    private fun filterDate(paymentList: MutableList<Payment>?) =
        paymentList?.filter { payment ->
            filterList.value?.any { filter ->
                filter.type == payment.type
                        && filter.initialDate <= payment.date
                        && filter.finishDate >= payment.date
            } ?: false
        }.orEmpty()


    private fun setPaymentList(paymentList: MutableList<Payment>?) {
        val listFilteredByDate = filterDate(paymentList).toMutableList()
        listFilteredByDate.sortDescending()
        _filteredPaymentList.value = listFilteredByDate
    }

    fun sumValues(): Double {
        var sum = Double.ZERO
        _filteredPaymentList.value?.forEach {
            sum += it.value
        }
        return sum
    }
}