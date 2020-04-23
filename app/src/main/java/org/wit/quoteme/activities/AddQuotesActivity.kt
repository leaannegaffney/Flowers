package org.wit.quoteme.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.*
import kotlinx.android.synthetic.main.activity_addquotes.*
import kotlinx.android.synthetic.main.activity_createnewcategory.*
import kotlinx.android.synthetic.main.card_quoteme.*
import kotlinx.android.synthetic.main.card_quoteme.categoryTitle
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.wit.quoteme.R
import org.wit.quoteme.main.MainApp
import org.wit.quoteme.models.QuoteMeModel

class AddQuotesActivity : AppCompatActivity(), AnkoLogger {
    lateinit var app: MainApp
    var category = QuoteMeModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addquotes)
        app = application as MainApp

        //I want to change the toolbar to display the category title that it's in
        //toolbarCategory.title = category.title
        //toolbarCategory.title = title
        category = intent.extras!!.getParcelable<QuoteMeModel>("category_edit")!!
        toolbarCategory.title = category.title
        setSupportActionBar(toolbarCategory)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
            }

        }
        return super.onOptionsItemSelected(item)
    }
}


