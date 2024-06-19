package com.example.organizafinancas.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.organizafinancas.domain.model.Category
import com.example.organizafinancas.domain.model.Payment
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface PaymentDao {

    @Query("SELECT * FROM payment")
    fun getAll(): Flow<List<Payment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg payments: Payment)

    @Query("SELECT * FROM payment " +
            "WHERE payment_method = :paymentTypeName " +
            "AND (date >= :initialDate AND date <= :endDate)" +
            "AND category IN (:categoriesFilters)")
    fun getByPaymentTypeAndCategory(
        paymentTypeName: String,
        initialDate: LocalDate,
        endDate: LocalDate,
        categoriesFilters: List<Category>
    ): List<Payment>
}