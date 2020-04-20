package org.wit.quoteme.models

interface QuoteMeStore {
    fun findAll(): List<QuoteMeModel>
    fun create(category: QuoteMeModel)
    fun update(category: QuoteMeModel)
    fun delete(category: QuoteMeModel)
}