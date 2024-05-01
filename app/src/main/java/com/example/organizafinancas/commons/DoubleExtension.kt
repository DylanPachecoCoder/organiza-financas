package com.example.organizafinancas.commons

import java.text.NumberFormat

fun Double?.toCurrency() = this?.let { NumberFormat.getCurrencyInstance().format(this) }
