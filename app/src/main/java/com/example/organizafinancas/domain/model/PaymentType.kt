package com.example.organizafinancas.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class PaymentType(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "payment_method_id")
    val id: Long = 0L,
    @ColumnInfo(name = "payment_method_name")
    override val name: String,
    @ColumnInfo(name = "payment_method_is_selected")
    override val isSelected: Boolean,
    @ColumnInfo(name = "payment_method_initial_date")
    val initialDate: LocalDate,
    @ColumnInfo(name = "payment_method_finish_date")
    val finishDate: LocalDate,
) : Filter