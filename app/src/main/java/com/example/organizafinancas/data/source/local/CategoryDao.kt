package com.example.organizafinancas.data.source.local

import com.example.organizafinancas.domain.model.SelectableFilter

interface CategoryDao {

    fun getAll(): List<SelectableFilter>

    fun save(category: SelectableFilter?): List<SelectableFilter>

    fun delete(category: SelectableFilter?): List<SelectableFilter>

    fun update(category: SelectableFilter?): List<SelectableFilter>

}
