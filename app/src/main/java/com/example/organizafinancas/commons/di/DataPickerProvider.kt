package com.example.organizafinancas.commons.di

import com.google.android.material.datepicker.MaterialDatePicker

fun provideDataPicker(title: String = TITLE) =
    MaterialDatePicker.Builder.dateRangePicker()
        .setTitleText(title)
        .build()

private const val TITLE = "Selecione o período desejado"