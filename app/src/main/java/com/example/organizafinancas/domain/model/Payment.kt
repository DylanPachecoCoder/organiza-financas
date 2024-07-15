package com.example.organizafinancas.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Payment(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "payment_id")
    val id: Long = 0L,
    @ColumnInfo(name = "payment_name")
    val name: String,
    @ColumnInfo(name = "payment_payment_method_id")
    val paymentMethodId: Long,
    @ColumnInfo(name = "payment_category_id")
    val categoryId: Long,
    @ColumnInfo(name = "payment_date")
    val date: LocalDate = LocalDate.now(),
    @ColumnInfo(name = "payment_value")
    val value: Double,
)
