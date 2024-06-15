package com.example.organizafinancas.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.organizafinancas.domain.model.PaymentType
import kotlinx.coroutines.flow.Flow

@Dao
interface PaymentTypeDao {

    @Query("SELECT * FROM paymenttype")
    fun getAll(): Flow<List<PaymentType>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg paymentType: PaymentType)
}
