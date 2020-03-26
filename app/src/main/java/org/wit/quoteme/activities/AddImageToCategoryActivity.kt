package org.wit.quoteme.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_addimagetocategory.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.quoteme.R
import org.wit.quoteme.helpers.readImage
import org.wit.quoteme.helpers.showImagePicker
import org.wit.quoteme.main.MainApp
import org.wit.quoteme.models.QuoteMeModel

class AddImageToCategoryActivity : AppCompatActivity(), AnkoLogger{

    var category = QuoteMeModel()
    lateinit var app : MainApp
    val imageRequest = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addimagetocategory)

        toolbarCategory.title = title
        toolbarCategory.setNavigationIcon(R.drawable.quote_bubble_foreground)
        setSupportActionBar(toolbarCategory)

        chooseImage.setOnClickListener {
            showImagePicker(this, imageRequest)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            imageRequest -> {
                if (data != null) {
                    category.image = data.getData().toString()
                    categoryImage.setImageBitmap(readImage(this, resultCode, data))
                }
            }
        }
    }
}