package com.example.organizafinancas.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class PaymentType(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "name")
    override val name: String,
    @ColumnInfo(name = "is_selected")
    override var isSelected: Boolean,
    @ColumnInfo(name = "initial_date")
    val initialDate: LocalDate,
    @ColumnInfo(name = "finish_date")
    val finishDate: LocalDate,
) : Filter