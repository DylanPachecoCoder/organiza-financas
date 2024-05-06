package com.example.organizafinancas.commons.extensions

import androidx.core.util.Pair
import com.example.organizafinancas.domain.model.PaymentFilter

fun PaymentFilter.getDataRange() =
    Pair(initialDate.toMilliseconds(), finishDate.toMilliseconds())