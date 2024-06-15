package com.example.organizafinancas.data.source.local

import androidx.room.TypeConverter
import com.example.organizafinancas.commons.extensions.toLocalDate
import com.example.organizafinancas.commons.extensions.toMilliseconds
import java.time.LocalDate

class Converters {

    @TypeConverter
    fun toLocalDateFromLong(date: LocalDate) = date.toMilliseconds()

    @TypeConverter
    fun toLongFromLocalDate(date: Long) = date.toLocalDate()
}