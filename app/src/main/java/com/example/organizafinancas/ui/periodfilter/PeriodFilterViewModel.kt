package com.example.organizafinancas.ui.periodfilter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.organizafinancas.data.Repository
import com.example.organizafinancas.domain.model.PaymentFilter
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset

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
        val first = createLocalDate(initialDate)
        val second = createLocalDate(finishDate)
        val paymentFilterEdited = paymentFilter.copy(initialDate = first, finishDate = second)
        repository.updateFilter(paymentFilter, paymentFilterEdited)
        fetchFilterList()
    }

    private fun createLocalDate(it: Long): LocalDate {
        val instant = Instant.ofEpochMilli(it)
        return instant.atZone(ZoneOffset.UTC).toLocalDate()
    }
}