package com.example.organizafinancas.domain.model

data class Purchase(
    val name: String = "Mercado pago",
    val type: String = "crédito",
    val date: String = "10/04/24",
    val value: String = "R$ 20,00",
)
