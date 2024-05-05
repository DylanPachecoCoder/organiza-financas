package com.example.organizafinancas.commons.extensions

import com.example.organizafinancas.commons.di.provideLocale
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun LocalDate.format() = format(DateTimeFormatter.ofPattern("dd MMM", provideLocale()))