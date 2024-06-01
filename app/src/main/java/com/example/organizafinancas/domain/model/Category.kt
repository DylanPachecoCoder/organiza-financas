package com.example.organizafinancas.domain.model

data class Category(
    override val name: String,
    override var isSelected: Boolean = true
) : Filter