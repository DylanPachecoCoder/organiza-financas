package com.example.organizafinancas.ui.paymentmethod

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.organizafinancas.data.repository.PaymentMethodRepository
import com.example.organizafinancas.domain.enums.PaymentTypeEnum
import com.example.organizafinancas.domain.model.PaymentMethod
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class PaymentMethodViewModel @Inject constructor(
    private val repository: PaymentMethodRepository
) : ViewModel() {

    fun insertPaymentMethod() {
        viewModelScope.launch {
            repository.savePaymentType(
                PaymentMethod(
                    name = PaymentTypeEnum.CASH.paymentType,
                    isSelected = true,
                    initialDate = LocalDate.of(2024, 5, 1),
                    finishDate = LocalDate.of(2024, 6, 30)
                )
            )
            repository.savePaymentType(
                PaymentMethod(
                    name = PaymentTypeEnum.CREDIT.paymentType,
                    isSelected = true,
                    initialDate = LocalDate.of(2024, 5, 1),
                    finishDate = LocalDate.of(2024, 6, 30)
                )
            )
        }
    }
}