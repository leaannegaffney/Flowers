package org.wit.quoteme.activities

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_quoteme.view.*
import org.wit.quoteme.R
import org.wit.quoteme.helpers.readImageFromPath
import org.wit.quoteme.models.QuoteMeModel

interface CategoryListener {
    fun onCategoryClick(category: QuoteMeModel)
}

class QuoteMeAdapter constructor(private var categories: List<QuoteMeModel>,
                                 private val listener: CategoryListener) : RecyclerView.Adapter<QuoteMeAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_quoteme, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val category = categories[holder.adapterPosition]
        holder.bind(category, listener)
    }

    override fun getItemCount(): Int = categories.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(category: QuoteMeModel, listener : CategoryListener) {
            itemView.categoryTitle.text = category.title
            itemView.imageIcon.setImageBitmap(readImageFromPath(itemView.context, category.image))
            itemView.setOnClickListener{ listener.onCategoryClick(category)}
        }
    }
}