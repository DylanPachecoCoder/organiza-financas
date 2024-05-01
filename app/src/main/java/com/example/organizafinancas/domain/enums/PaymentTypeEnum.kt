package com.example.organizafinancas.domain.enums

enum class PaymentTypeEnum(val paymentType: String) {
    CREDITO("crédito"),
    DEBITO("débito"),
    PIX("pix"),
    VR("VR"),
    VA("VA"),
    DINHEIRO("dinheiro"),
}