package org.wit.flowers.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_flowers.view.*
import kotlinx.android.synthetic.main.card_flowers.view.flowerName
import org.wit.flowers.R
import org.wit.flowers.helpers.readImageFromPath
import org.wit.flowers.models.FlowerModel

interface FlowerListener {
    fun onFlowerClick(flower: FlowerModel)
}

class FlowerAdapter constructor(private var flowers: List<FlowerModel>,
                                 private val listener: FlowerListener
) : RecyclerView.Adapter<FlowerAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
            LayoutInflater.from(
                parent?.context
            ).inflate(R.layout.card_flowers, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val flower = flowers[holder.adapterPosition]
        holder.bind(flower, listener)
    }

    override fun getItemCount(): Int = flowers.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(flower: FlowerModel, listener : FlowerListener) {
            itemView.flowerName.text = flower.name
            itemView.imageIcon.setImageBitmap(readImageFromPath(itemView.context, flower.image))
            itemView.setOnClickListener{ listener.onFlowerClick(flower)}
        }
    }
}