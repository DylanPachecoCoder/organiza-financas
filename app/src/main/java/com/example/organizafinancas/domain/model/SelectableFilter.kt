package com.example.organizafinancas.domain.model

open class SelectableFilter(
    name: String,
    var isSelected: Boolean = true
): Filter(name)