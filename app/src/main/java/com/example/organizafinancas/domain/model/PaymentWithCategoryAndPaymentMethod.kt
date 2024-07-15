package com.example.organizafinancas.domain.model

import androidx.room.Embedded
import androidx.room.Relation

data class PaymentWithCategoryAndPaymentMethod(
    @Embedded
    val payment: Payment,
    @Relation(
        parentColumn = "payment_category_id",
        entityColumn = "category_id"
    )
    val category: Category,
    @Relation(
        parentColumn = "payment_payment_method_id",
        entityColumn = "payment_method_id"
    )
    val paymentMethod: PaymentType
)
