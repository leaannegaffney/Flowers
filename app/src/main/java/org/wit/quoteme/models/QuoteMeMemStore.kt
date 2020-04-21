package org.wit.quoteme.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class QuoteMeMemStore : QuoteMeStore, AnkoLogger {

    val categories = ArrayList<QuoteMeModel>()

    override fun findAll(): List<QuoteMeModel> {
        return categories
    }

    override fun create(category: QuoteMeModel) {
        category.id = getId()
        categories.add(category)
        logAll()
    }

    override fun update(category: QuoteMeModel) {
        var foundCategory: QuoteMeModel? = categories.find { p -> p.id == category.id }
        if (foundCategory != null) {
            foundCategory.title = category.title
            foundCategory.image = category.image
            logAll()
        }
    }

    fun logAll() {
        categories.forEach{ info("${it}") }
    }

    override fun delete(category: QuoteMeModel) {
        categories.remove(category)
    }

}