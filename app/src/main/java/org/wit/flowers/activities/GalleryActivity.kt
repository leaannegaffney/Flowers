package org.wit.flowers.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_gallery.*
import kotlinx.android.synthetic.main.activity_gallery.recyclerView
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivityForResult
import org.wit.flowers.R
import org.wit.flowers.adapter.GalleryImageAdapter
import org.wit.flowers.adapter.GalleryImageClickListener
import org.wit.flowers.adapter.Image
import org.wit.flowers.fragment.GalleryFullscreenFragment

class GalleryActivity : AppCompatActivity(), GalleryImageClickListener, AnkoLogger {

    private val SPAN_COUNT = 2
    private val imageList = ArrayList<Image>()
    lateinit var galleryAdapter: GalleryImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        galleryAdapter = GalleryImageAdapter(imageList)
        galleryAdapter.listener = this

        recyclerView.layoutManager = GridLayoutManager(this, SPAN_COUNT)
        recyclerView.adapter = galleryAdapter

        loadImages()

        toolbarGallery.title = title
        setSupportActionBar(toolbarGallery)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_gallery, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                info("Back arrow pressed")
                startActivityForResult<ListFlowersActivity>(0)
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadImages() {
        imageList.add(Image("https://i.ibb.co/RNn6C9y/image1.jpg", "Pink Dahlia"))
        imageList.add(Image("https://i.ibb.co/P9cgMr1/image2.jpg" , "Pink Rose"))
        imageList.add(Image("https://i.ibb.co/GCYyk5x/image3.jpg", "Sunflower"))
        imageList.add(Image("https://i.ibb.co/qNMGycd/image4.jpg", "Pink Daisies"))
        imageList.add(Image("https://i.ibb.co/Jmqg8qY/image5.jpg", "Dahlia Mixture"))
        imageList.add(Image("https://i.ibb.co/PtdD8J9/image6.jpg", "Orange Dahlia"))
        imageList.add(Image("https://i.ibb.co/hW0JSBt/image7.jpg", "Flower Bed"))
        imageList.add(Image("https://i.ibb.co/5BX4z2c/image8.jpg", "Pink Flower House"))
        imageList.add(Image("https://i.ibb.co/rmz9VCP/image9.jpg", "Tulip Bed"))
        galleryAdapter.notifyDataSetChanged()
    }

    override fun onClick(position: Int) {
        val bundle = Bundle()
        bundle.putSerializable("images", imageList)
        bundle.putInt("position", position)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val galleryFragment = GalleryFullscreenFragment()
        galleryFragment.setArguments(bundle)
        galleryFragment.show(fragmentTransaction, "gallery")
    }
}