package com.example.organizafinancas.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.organizafinancas.domain.model.Payment
import kotlinx.coroutines.flow.Flow

@Dao
interface PaymentDao {

    @Query("SELECT * FROM payment")
    fun getAll(): Flow<List<Payment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg payments: Payment)

    @Query(
        "SELECT * FROM payment " +
                "JOIN paymenttype ON paymenttype.is_selected = 1 " +
                "AND paymenttype.id = payment_method_id " +
                "AND date >= paymenttype.initial_date " +
                "AND date <= paymenttype.finish_date " +
                "JOIN category ON category.is_selected = 1 " +
                "AND category.name = payment.category"
    )
    fun getByPaymentTypeAndCategory(): Flow<List<Payment>>
}