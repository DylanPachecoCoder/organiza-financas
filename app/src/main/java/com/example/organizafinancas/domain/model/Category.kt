package com.example.organizafinancas.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "name")
    override val name: String,
    @ColumnInfo(name = "is_selected")
    override var isSelected: Boolean = true
) : Filter