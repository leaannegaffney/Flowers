package org.wit.quoteme.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_quoteme.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.quoteme.R
import org.wit.quoteme.main.MainApp
import org.wit.quoteme.models.QuoteMeModel

class QuoteMeActivity : AppCompatActivity(), AnkoLogger {

    var category = QuoteMeModel()
    lateinit var app : MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quoteme)
        app = application as MainApp

        var edit = false

        if (intent.hasExtra("category_edit")) {
            edit = true
            category = intent.extras!!.getParcelable<QuoteMeModel>("category_edit")!!
            categoryTitle.setText(category.title)
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

        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_category, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
