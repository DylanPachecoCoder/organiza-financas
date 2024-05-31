package com.example.organizafinancas.data.source.local

import com.example.organizafinancas.domain.model.SelectableFilter
import javax.inject.Inject

class CategoryDaoImpl @Inject constructor() : CategoryDao {

    override fun getAll(): List<SelectableFilter> = getListOfCategories()

    override fun save(category: SelectableFilter) {
        categoryFilterList.add(category)
    }

    override fun delete(category: SelectableFilter) {
        categoryFilterList.remove(category)
    }

    override fun update(category: SelectableFilter) {
        categoryFilterList.forEach {
            if (it.hashCode() == category.hashCode()) {
                categoryFilterList.remove(it)
                categoryFilterList.add(category)
                return
            }
        }
    }

    private fun getListOfCategories(): List<SelectableFilter> {
        val newList = mutableListOf<SelectableFilter>()
        newList.addAll(categoryFilterList)
        return newList
    }

    private val categoryFilterList =
        mutableListOf(
            SelectableFilter("restaurante"),
            SelectableFilter("sem categoria"),
            SelectableFilter("carro"),
            SelectableFilter("mercado"),
            SelectableFilter("comida"),
            SelectableFilter("sair"),
        )
}