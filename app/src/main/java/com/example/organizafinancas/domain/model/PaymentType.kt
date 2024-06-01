package com.example.organizafinancas.domain.model

import java.time.LocalDate

data class PaymentType(
    override val name: String,
    override var isSelected: Boolean,
    var initialDate: LocalDate,
    var finishDate: LocalDate,
) : Filter