package org.wit.quoteme.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import kotlinx.android.synthetic.main.activity_addquotes.*
import kotlinx.android.synthetic.main.activity_createnewcategory.*
import kotlinx.android.synthetic.main.activity_listcategories.*
import kotlinx.android.synthetic.main.card_quoteme.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.wit.quoteme.R
import org.wit.quoteme.helpers.readImage
import org.wit.quoteme.helpers.readImageFromPath
import org.wit.quoteme.helpers.showImagePicker
import org.wit.quoteme.main.MainApp
import org.wit.quoteme.models.QuoteMeModel

class AddQuotesActivity : AppCompatActivity(), AnkoLogger {
    lateinit var app: MainApp
    var category = QuoteMeModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addquotes)
        app = application as MainApp

        toolbarCategory.title = title
        setSupportActionBar(toolbarCategory)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        category = intent.extras!!.getParcelable<QuoteMeModel>("category_edit")!!
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_category, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                info("Back arrow pressed")
                finish()
            }

            R.id.item_add -> {
                //Add images of quotes saved on phone to a grid view

            }

            R.id.item_edit -> {
                startActivityForResult(intentFor<CreateNewCategoryActivity>().putExtra("category_edit", category), 0)


                finish()
                //opens the right screen but is not linking to the current category so isn't updating or deleting anything
            }

        }
        return super.onOptionsItemSelected(item)
    }
}


