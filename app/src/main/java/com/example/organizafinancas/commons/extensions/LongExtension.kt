package com.example.organizafinancas.commons.extensions

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset

fun Long.toLocalDate(): LocalDate =
    Instant.ofEpochMilli(this).atZone(ZoneOffset.UTC).toLocalDate()