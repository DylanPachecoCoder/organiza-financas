package com.example.organizafinancas.data.source.local

import com.example.organizafinancas.domain.model.SelectableFilter

interface CategoryDao {

    fun getAll(): List<SelectableFilter>

    fun save(category: SelectableFilter)

    fun delete(category: SelectableFilter)

    fun update(category: SelectableFilter)
}
