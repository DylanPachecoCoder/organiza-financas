package com.example.organizafinancas.data.repository

import com.example.organizafinancas.domain.model.Payment
import com.example.organizafinancas.domain.model.PaymentTypeFilter
import com.example.organizafinancas.domain.model.SelectableFilter
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface Repository  {

    fun fetchFilters(): Flow<MutableList<SelectableFilter>>

    fun fetchPayments(): Flow<List<Payment>>

    fun fetchPaymentFilters(): Flow<List<PaymentTypeFilter>>

    fun fetchCategoryFilters(): Flow<List<SelectableFilter>>

    fun saveCategory(category: SelectableFilter?): List<SelectableFilter>

    fun deleteCategory(category: SelectableFilter?): List<SelectableFilter>

    fun updateCategory(category: SelectableFilter?): List<SelectableFilter>
}