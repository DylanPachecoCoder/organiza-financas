package com.example.organizafinancas.commons.extensions

import com.example.organizafinancas.commons.di.provideLocale
import java.text.NumberFormat

val Double.Companion.ZERO
    get() = 0.0

fun Double?.toCurrency() =
    this?.let { NumberFormat.getCurrencyInstance(provideLocale()).format(this) }
