package com.example.organizafinancas.commons.extensions

import androidx.core.util.Pair
import com.example.organizafinancas.domain.model.PaymentMethod

fun PaymentMethod.getDataRange() =
    Pair(initialDate.toMilliseconds(), finishDate.toMilliseconds())