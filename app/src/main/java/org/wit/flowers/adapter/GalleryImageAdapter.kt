package org.wit.flowers.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.engine.DiskCacheStrategy
import org.wit.flowers.R
import org.wit.flowers.helpers.GlideApp
import kotlinx.android.synthetic.main.item_gallery_image.view.*

//Some code taken from https://thesimplycoder.com/137/android-image-gallery-using-kotlin-tutorial/
class GalleryImageAdapter(private val itemList: List<Image>) : RecyclerView.Adapter<GalleryImageAdapter.ViewHolder>() {

    private var context: Context? = null
    var listener: GalleryImageClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_gallery_image, parent,
            false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            val image = itemList.get(adapterPosition)
            GlideApp.with(context!!)
                .load(image.imageUrl)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(itemView.ivGalleryImage)

            itemView.container.setOnClickListener {
                listener?.onClick(adapterPosition)
            }
        }
    }
}