package org.wit.quoteme.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import kotlinx.android.synthetic.main.activity_addquotes.view.*
import kotlinx.android.synthetic.main.activity_createnewcategory.*
import kotlinx.android.synthetic.main.activity_listquotes.*
import kotlinx.android.synthetic.main.card_quoteme.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import org.wit.quoteme.R
import org.wit.quoteme.adapter.CategoryAdapter
import org.wit.quoteme.adapter.CategoryListener
import org.wit.quoteme.adapter.QuoteMeAdapter
import org.wit.quoteme.main.MainApp
import org.wit.quoteme.models.CategoryModel
import org.wit.quoteme.models.QuoteMeModel

class ListQuotesActivity : AppCompatActivity(), AnkoLogger {
    lateinit var app: MainApp
    var category = QuoteMeModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listquotes)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        loadQuotes()

        category = intent.extras!!.getParcelable<QuoteMeModel>("category_edit")!!
        toolbarCategory.title = category.title
        setSupportActionBar(toolbarCategory)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener {
            startActivityForResult<AddQuotesActivity>(0)
        }
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

            R.id.item_edit -> {
                startActivityForResult(intentFor<CreateNewCategoryActivity>().putExtra("category_edit", category), 0)
                finish()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadQuotes()
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun loadQuotes() {
        showQuotes(app.quotes.findAll())
    }

    fun showQuotes(quotes: List<CategoryModel>) {
        recyclerView.adapter = CategoryAdapter(quotes)
        recyclerView.adapter?.notifyDataSetChanged()
    }
}

