package org.wit.quoteme.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.quoteme.helpers.*
import java.util.*

/*val JSON_FILE = "quotes.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<CategoryModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}*/

//class CategoryJSONStore : CategoryStore, AnkoLogger {
    /*val context: Context
    var quotes = mutableListOf<CategoryModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<CategoryModel> {
        return quotes
    }

    override fun create(quote: CategoryModel) {
        quote.id = generateRandomId()
        quotes.add(quote)
        serialize()
    }

    override fun update(quote: CategoryModel) {
        val quotesList = findAll() as ArrayList<CategoryModel>
        var foundQuote: CategoryModel? = quotesList.find{p -> p.id == quote.id}
        if (foundQuote != null) {
            foundQuote.title = quote.title
            foundQuote.image = quote.image
        }
        serialize()
    }

    override fun delete(quote: CategoryModel) {
        quotes.remove(quote)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(quotes, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        quotes = Gson().fromJson(jsonString, listType)
    }

    override fun search(searchTerm: String) : List<CategoryModel> {
        return quotes.filter { quote -> quote.title.contains(searchTerm)}
    }*/
//}