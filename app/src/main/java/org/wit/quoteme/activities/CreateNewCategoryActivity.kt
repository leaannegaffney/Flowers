package org.wit.quoteme.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_addquotes.*
import kotlinx.android.synthetic.main.activity_createnewcategory.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import org.wit.quoteme.R
import org.wit.quoteme.helpers.readImage
import org.wit.quoteme.helpers.readImageFromPath
import org.wit.quoteme.helpers.showImagePicker
import org.wit.quoteme.main.MainApp
import org.wit.quoteme.models.QuoteMeModel

class CreateNewCategoryActivity : AppCompatActivity(), AnkoLogger {

    var category = QuoteMeModel()
    lateinit var app : MainApp
    var edit = false
    val IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createnewcategory)
        app = application as MainApp

        toolbarAdd.title = title
        toolbarAdd.setNavigationIcon(R.drawable.quote_bubble_foreground)
        setSupportActionBar(toolbarAdd)


        if (intent.hasExtra("category_edit")) {
            edit = true
            category = intent.extras!!.getParcelable<QuoteMeModel>("category_edit")!!
            categoryTitle.setText(category.title)
            categoryImage.setImageBitmap(readImageFromPath(this, category.image))
            if (category.image != null){
                chooseImage.setText(R.string.change_category_image)
            }
            btnAdd.setText(R.string.save_category)
        }


        btnAdd.setOnClickListener() {
            category.title = categoryTitle.text.toString()
            if (category.title.isEmpty()) {
                toast(R.string.enter_category_title)
            }else{
                if (edit){
                    app.categories.update(category.copy())
                }else {
                    app.categories.create(category.copy())
                }
                info("add Button Pressed: $categoryTitle")
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            }
        }

        chooseImage.setOnClickListener{
            showImagePicker(this, IMAGE_REQUEST)
        }



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_editcategory, menu)
        if (edit && menu != null) menu.getItem(0).setVisible(true)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_cancel -> {
                finish()
            }
            R.id.item_delete -> {
                app.categories.delete(category)
                startActivityForResult(intentFor<ListCategoriesActivity>(),0)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    category.image = data.getData().toString()
                    categoryImage.setImageBitmap(readImage(this, resultCode, data))
                    chooseImage.setText(R.string.change_category_image)
                }
            }
        }
    }
}
