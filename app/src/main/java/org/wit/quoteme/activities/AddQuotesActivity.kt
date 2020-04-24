package org.wit.quoteme.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.*
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
import org.wit.quoteme.models.CategoryModel
import org.wit.quoteme.models.QuoteMeModel

class AddQuotesActivity : AppCompatActivity(), AnkoLogger {

    lateinit var app: MainApp
    var quote = CategoryModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addquotes)
        app = application as MainApp

        toolbarQuote.title = title
        toolbarQuote.setNavigationIcon(R.drawable.quote_bubble_foreground)
        setSupportActionBar(toolbarQuote)

        btnAddQuote.setOnClickListener() {
            quote.text = quoteText.text.toString()

            if(quote.text.isEmpty()) {
                toast("Please enter a quote")
            } else {
                app.quotes.create(quote.copy())
            }
            info("Add quote button pressed : $quoteText")
            setResult(AppCompatActivity.RESULT_OK)
            finish()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_addquote, menu)
        return super.onCreateOptionsMenu(menu)
    }

      fun onOptionsItemsSelected(item: MenuItem?) : Boolean {
        when (item?.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}