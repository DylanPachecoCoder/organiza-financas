package com.example.organizafinancas.data.source.local

import com.example.organizafinancas.domain.model.Category
import javax.inject.Inject

class CategoryDaoImpl @Inject constructor() : CategoryDao {

    override fun getAll(): List<Category> = getListOfCategories()

    override fun save(category: Category) {
        categoryFilterList.add(category)
    }

    override fun delete(category: Category) {
        categoryFilterList.remove(category)
    }

    override fun update(category: Category) {
        categoryFilterList.forEach {
            if (it.hashCode() == category.hashCode()) {
                categoryFilterList.remove(it)
                categoryFilterList.add(category)
                return
            }
        }
    }

    private fun getListOfCategories(): List<Category> {
        val newList = mutableListOf<Category>()
        newList.addAll(categoryFilterList)
        return newList
    }

    private val categoryFilterList =
        mutableListOf(
            Category("restaurante"),
            Category("sem categoria"),
            Category("carro"),
            Category("mercado"),
            Category("comida"),
            Category("sair"),
        )
}