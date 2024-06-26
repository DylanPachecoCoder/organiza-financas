package com.example.organizafinancas.domain.enums

enum class PaymentTypeEnum(val paymentType: String) {
    CREDIT("crédito"),
    DEBIT("débito"),
    PIX("pix"),
    CASH("dinheiro"),
}