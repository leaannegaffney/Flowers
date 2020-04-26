package org.wit.flowers.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.flowers.helpers.exists
import org.wit.flowers.helpers.read
import org.wit.flowers.helpers.write
import java.util.*
import kotlin.collections.ArrayList

val JSON_FILE = "flowers.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<FlowerModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class FlowerJSONStore : FlowerStore, AnkoLogger {
    val context: Context
    var flowers = mutableListOf<FlowerModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<FlowerModel> {
        return flowers
    }

    override fun create(flower: FlowerModel) {
        flower.id = generateRandomId()
        flowers.add(flower)
        serialize()
    }

    override fun update(flower: FlowerModel) {
        val flowersList = findAll() as ArrayList<FlowerModel>
        var foundflower: FlowerModel? = flowersList.find{p -> p.id == flower.id}
        if (foundflower != null) {
            foundflower.name = flower.name
            foundflower.image = flower.image
        }
        serialize()
    }

    override fun delete(flower: FlowerModel) {
        flowers.remove(flower)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(flowers, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        flowers = Gson().fromJson(jsonString, listType)
    }

    override fun search(searchTerm: String) : List<FlowerModel> {
        return flowers.filter { flower -> flower.name.contains(searchTerm)}
    }
}