package org.wit.flowers.models

interface FlowerStore {
    fun search(searchTerm: String) : List<FlowerModel>
    fun findAll(): List<FlowerModel>
    fun create(flower: FlowerModel)
    fun update(flower: FlowerModel)
    fun delete(flower: FlowerModel)
}