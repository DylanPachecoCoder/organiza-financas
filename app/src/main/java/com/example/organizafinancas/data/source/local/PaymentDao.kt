package com.example.organizafinancas.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.organizafinancas.domain.model.Payment
import com.example.organizafinancas.domain.model.PaymentWithCategoryAndPaymentMethod
import kotlinx.coroutines.flow.Flow

@Dao
interface PaymentDao {

    @Query("SELECT * FROM payment")
    fun getAll(): Flow<List<Payment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg payments: Payment)

    @Query(
        "SELECT * FROM payment " +
                "LEFT JOIN paymentmethod ON paymentmethod.payment_method_is_selected = 1 " +
                "AND paymentmethod.payment_method_id = payment.payment_payment_method_id " +
                "AND payment.payment_date >= paymentmethod.payment_method_initial_date " +
                "AND payment.payment_date <= paymentmethod.payment_method_finish_date " +
                "LEFT JOIN category ON category.category_is_selected = 1 " +
                "AND category.category_id = payment.payment_category_id"
    )
    fun getByPaymentTypeAndCategory(): Flow<List<PaymentWithCategoryAndPaymentMethod>>
}