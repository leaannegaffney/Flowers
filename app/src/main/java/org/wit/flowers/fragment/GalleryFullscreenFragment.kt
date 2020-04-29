package org.wit.flowers.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.image_fullscreen.view.*
import org.wit.flowers.R
import org.wit.flowers.adapter.Image
import org.wit.flowers.helpers.GlideApp
import org.wit.flowers.helpers.ZoomOutPageTransformer

//Code taken from https://thesimplycoder.com/137/android-image-gallery-using-kotlin-tutorial/
class GalleryFullscreenFragment : DialogFragment() {
    private var imageList = ArrayList<Image>()
    private var selectedPosition: Int = 0
    lateinit var tvGalleryTitle: TextView
    lateinit var viewPager: ViewPager
    lateinit var galleryPagerAdapter: GalleryPagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_gallery_fullscreen, container, false)

        viewPager = view.findViewById(R.id.viewPager)
        tvGalleryTitle = view.findViewById(R.id.tvGalleryTitle)
        galleryPagerAdapter = GalleryPagerAdapter()
        imageList = arguments?.getSerializable("images") as ArrayList<Image>
        selectedPosition = arguments!!.getInt("position")
        viewPager.adapter = galleryPagerAdapter
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener)
        viewPager.setPageTransformer(true, ZoomOutPageTransformer())
        setCurrentItem(selectedPosition)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
    }

    private fun setCurrentItem(position: Int) {
        viewPager.setCurrentItem(position, false)
    }

    internal var viewPagerPageChangeListener: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                tvGalleryTitle.text = imageList.get(position).title
            }

            override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {
            }

            override fun onPageScrollStateChanged(arg0: Int) {
            }
        }

    inner class GalleryPagerAdapter : PagerAdapter() {
        override fun instantiateItem(container: ViewGroup, position: Int): Any {

            val layoutInflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = layoutInflater.inflate(R.layout.image_fullscreen, container, false)
            val image = imageList.get(position)

            GlideApp.with(context!!)
                .load(image.imageUrl)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view.ivFullscreenImage)
            container.addView(view)
            return view
        }

        override fun getCount(): Int {
            return imageList.size
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view === obj as View
        }

        override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
            container.removeView(obj as View)
        }
    }
}
