package com.example.organizafinancas.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.organizafinancas.domain.model.Category
import com.example.organizafinancas.domain.model.Payment
import com.example.organizafinancas.domain.model.PaymentMethod

@Database(entities = [Category::class, PaymentMethod::class, Payment::class], version = 9, exportSchema = true)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun categoryDao(): CategoryDao

    abstract fun paymentTypeDao(): PaymentMethodDao

    abstract fun paymentDao(): PaymentDao
}