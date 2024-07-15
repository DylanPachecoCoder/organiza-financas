package com.example.organizafinancas.data.source.local

import androidx.room.TypeConverter
import com.example.organizafinancas.commons.extensions.toLocalDate
import com.example.organizafinancas.commons.extensions.toMilliseconds
import com.example.organizafinancas.domain.model.Category
import java.time.LocalDate

class Converters {

    @TypeConverter
    fun toLocalDateFromLong(date: LocalDate) = date.toMilliseconds()

    @TypeConverter
    fun toLongFromLocalDate(date: Long) = date.toLocalDate()

    @TypeConverter
    fun toCategoryFromCategoryName(category: Category) = category.name
}