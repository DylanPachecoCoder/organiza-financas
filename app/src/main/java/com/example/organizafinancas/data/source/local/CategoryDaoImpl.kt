package com.example.organizafinancas.data.source.local

import com.example.organizafinancas.domain.model.SelectableFilter
import javax.inject.Inject

class CategoryDaoImpl @Inject constructor(): CategoryDao {

    override fun getAll(): List<SelectableFilter> = categoryFilterList

    override fun save(category: SelectableFilter?): List<SelectableFilter> {
        category?.also { categoryFilterList.add(category) }
        return categoryFilterList
    }

    override fun delete(category: SelectableFilter?): List<SelectableFilter> {
        category?.also { categoryFilterList.remove(category) }
        return categoryFilterList
    }

    override fun update(category: SelectableFilter?): List<SelectableFilter> {
        category?.also {
            categoryFilterList.forEach {
                if (it.hashCode() == category.hashCode()) {
                    categoryFilterList.remove(it)
                    categoryFilterList.add(category)
                }
            }
        }
        return categoryFilterList
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