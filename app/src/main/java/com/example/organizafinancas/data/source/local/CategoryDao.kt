package com.example.organizafinancas.data.source.local

import com.example.organizafinancas.domain.model.Category

interface CategoryDao {

    fun getAll(): List<Category>

    fun save(category: Category)

    fun delete(category: Category)

    fun update(category: Category)
}
