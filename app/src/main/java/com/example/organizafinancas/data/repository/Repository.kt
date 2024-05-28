package com.example.organizafinancas.data.repository

import com.example.organizafinancas.domain.model.Payment
import com.example.organizafinancas.domain.model.PaymentTypeFilter
import com.example.organizafinancas.domain.model.SelectableFilter
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface Repository  {

    fun fetchFilters(): Flow<MutableList<SelectableFilter>>

    fun fetchPayments(): Flow<MutableList<Payment>>

    fun fetchPaymentFilters(): Flow<MutableList<PaymentTypeFilter>>

    fun fetchCategoryFilters(): Flow<MutableList<SelectableFilter>>

    fun saveCategory(category: SelectableFilter?): MutableList<SelectableFilter>

    fun deleteCategory(category: SelectableFilter?): MutableList<SelectableFilter>

    fun updateCategory(category: SelectableFilter?): MutableList<SelectableFilter>
}