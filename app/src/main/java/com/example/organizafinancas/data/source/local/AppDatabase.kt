package com.example.organizafinancas.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.organizafinancas.domain.model.Category

@Database(entities = [Category::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
}