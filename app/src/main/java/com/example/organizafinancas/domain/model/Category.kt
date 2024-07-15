package com.example.organizafinancas.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "category_id")
    val id: Long = 0L,
    @ColumnInfo(name = "category_name")
    override val name: String,
    @ColumnInfo(name = "category_is_selected")
    override var isSelected: Boolean = true
) : Filter