package org.wit.quoteme.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_addquotes.view.*
import kotlinx.android.synthetic.main.card_category.view.*
import org.wit.quoteme.R
import org.wit.quoteme.models.CategoryModel
import org.wit.quoteme.helpers.readImageFromPath

class CategoryAdapter constructor(private var quotes: List<CategoryModel>) : RecyclerView.Adapter<CategoryAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_category, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val quote = quotes[holder.adapterPosition]
        holder.bind(quote)
    }

    override fun getItemCount(): Int = quotes.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(quote : CategoryModel) {
            //itemView.quoteText.text = quote.text
            itemView.quoteText.setText(quote.text)
        }
    }
}
