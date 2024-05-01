package com.example.organizafinancas.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.organizafinancas.domain.enums.PaymentTypeEnum
import com.example.organizafinancas.domain.model.Payment
import kotlinx.coroutines.launch
import java.time.LocalDate

class HomeViewModel : ViewModel() {

    private var paymentList: List<Payment>? = null

    private val _filterList = MutableLiveData<List<PaymentTypeEnum>>()
    val filterList: LiveData<List<PaymentTypeEnum>> = _filterList

    private val _filteredPaymentList = MutableLiveData<MutableList<Payment>?>()
    val filteredPaymentList: LiveData<MutableList<Payment>?> = _filteredPaymentList

    fun fetchFilterList() {
        viewModelScope.launch {
            _filterList.value = provideFilterList()
        }
    }

    fun fetchPaymentList() {
        viewModelScope.launch {
            paymentList = providePurchaseList()
            setPaymentList(paymentList?.toMutableList())
        }
    }

    fun filterList(option: PaymentTypeEnum, isChecked: Boolean) {
        val selectedPaymentsList = paymentList?.filter { it.type == option }.orEmpty()
        val currentPaymentList = _filteredPaymentList.value
        if (isChecked) {
            currentPaymentList?.addAll(selectedPaymentsList)
        }else{
            currentPaymentList?.removeAll(selectedPaymentsList)
        }
        setPaymentList(currentPaymentList)
    }

    private fun setPaymentList(currentPaymentList: MutableList<Payment>?) {
        currentPaymentList?.sortDescending()
        _filteredPaymentList.value = currentPaymentList
    }

    private fun provideFilterList() =
        mutableListOf(
            PaymentTypeEnum.CREDIT,
            PaymentTypeEnum.DEBIT,
            PaymentTypeEnum.CASH,
            PaymentTypeEnum.PIX,
        )

    private fun providePurchaseList() =
        mutableListOf(
            Payment(
                type = PaymentTypeEnum.CREDIT,
                date = LocalDate.of(2024,4,10)
            ),
            Payment(
                type = PaymentTypeEnum.DEBIT,
                date = LocalDate.of(2024,4,9)
            ),
            Payment(
                type = PaymentTypeEnum.PIX,
                date = LocalDate.of(2024,4,8)
            ),
            Payment(type = PaymentTypeEnum.CASH),
            Payment(type = PaymentTypeEnum.CREDIT),
            Payment(type = PaymentTypeEnum.CREDIT),
            Payment(type = PaymentTypeEnum.DEBIT),
            Payment(type = PaymentTypeEnum.DEBIT),
            Payment(type = PaymentTypeEnum.PIX),
        )
}