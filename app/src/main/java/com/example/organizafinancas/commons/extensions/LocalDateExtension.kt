package com.example.organizafinancas.commons.extensions

import com.example.organizafinancas.commons.di.provideLocale
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

const val DATE_PATTERN = "dd MMM"

fun LocalDate?.format() =
    this?.format(DateTimeFormatter.ofPattern(DATE_PATTERN, provideLocale()))

fun LocalDate.toMilliseconds() = atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()