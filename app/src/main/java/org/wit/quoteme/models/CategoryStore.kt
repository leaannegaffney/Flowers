package org.wit.quoteme.models

interface CategoryStore {
    //fun search(searchTerm: String) : List<CategoryModel>
    fun findAll(): List<CategoryModel>
    fun create(quote: CategoryModel)
    fun update(quote: CategoryModel)
    fun delete(quote: CategoryModel)
}