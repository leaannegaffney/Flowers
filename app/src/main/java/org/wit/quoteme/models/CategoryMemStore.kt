package org.wit.quoteme.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.util.*
import kotlin.collections.ArrayList

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class CategoryMemStore : CategoryStore, AnkoLogger {
    val quotes = ArrayList<CategoryModel>()

    override fun findAll(): List<CategoryModel> {
        return quotes
    }

    override fun create(quote: CategoryModel) {
        quote.id = getId()
        quotes.add(quote)
        logAll()
    }

    override fun update(quote: CategoryModel) {
        var foundQuote: CategoryModel? = quotes.find{ p -> p.id == quote.id}
        if ( foundQuote != null) {
            foundQuote.text = quote.text
            logAll()
        }
    }

    override fun delete(quote: CategoryModel) {
        quotes.remove(quote)
    }

    fun logAll() {
        quotes.forEach{ info( "${it}")}
    }
}