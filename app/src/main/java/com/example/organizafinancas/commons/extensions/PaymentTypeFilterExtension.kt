package com.example.organizafinancas.commons.extensions

import androidx.core.util.Pair
import com.example.organizafinancas.domain.model.PaymentType

fun PaymentType.getDataRange() =
    Pair(initialDate.toMilliseconds(), finishDate.toMilliseconds())