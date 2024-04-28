package com.example.organizafinancas.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.organizafinancas.domain.enums.PaymentTypeEnum
import com.example.organizafinancas.domain.model.Payment
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _filterList = MutableLiveData<List<String>>()
    val filterList: LiveData<List<String>> = _filterList

    private val _paymentList = MutableLiveData<List<Payment>>()
    val paymentList: LiveData<List<Payment>> = _paymentList

    fun fetchFilterList(){
        viewModelScope.launch {
            _filterList.value = provideFilterList()
        }
    }

    fun fetchPaymentList(){
        viewModelScope.launch {
            _paymentList.value = providePurchaseList()
        }
    }

    private fun provideFilterList() =
        mutableListOf("credito", "debito", "dinheiro", "pix", "vr", "va")

    private fun providePurchaseList() =
        mutableListOf(
            Payment(type = PaymentTypeEnum.CREDITO),
            Payment(type = PaymentTypeEnum.DEBITO),
            Payment(type = PaymentTypeEnum.PIX),
            Payment(type = PaymentTypeEnum.DINHEIRO),
            Payment(type = PaymentTypeEnum.VR),
            Payment(type = PaymentTypeEnum.VR),
            Payment(type = PaymentTypeEnum.CREDITO),
            Payment(type = PaymentTypeEnum.VA),
            Payment(type = PaymentTypeEnum.VA),
            Payment(type = PaymentTypeEnum.CREDITO),
            Payment(type = PaymentTypeEnum.DEBITO),
            Payment(type = PaymentTypeEnum.DEBITO),
        )
}