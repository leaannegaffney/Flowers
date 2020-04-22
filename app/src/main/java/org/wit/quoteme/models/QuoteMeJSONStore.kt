package org.wit.quoteme.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.quoteme.helpers.*
import java.util.*

val JSON_FILE = "categories.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<QuoteMeModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class QuoteMeJSONStore : QuoteMeStore, AnkoLogger {
    val context: Context
    var categories = mutableListOf<QuoteMeModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<QuoteMeModel> {
        return categories
    }

    override fun create(category: QuoteMeModel) {
        category.id = generateRandomId()
        categories.add(category)
        serialize()
    }

    override fun update(category: QuoteMeModel) {
        val categoriesList = findAll() as ArrayList<QuoteMeModel>
        var foundCategory: QuoteMeModel? = categoriesList.find{p -> p.id == category.id}
        if (foundCategory != null) {
            foundCategory.title = category.title
            foundCategory.image = category.image
        }
        serialize()
    }

    override fun delete(category: QuoteMeModel) {
        categories.remove(category)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(categories, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        categories = Gson().fromJson(jsonString, listType)
    }
}