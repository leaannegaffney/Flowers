package org.wit.quoteme.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import kotlinx.android.synthetic.main.activity_listcategories.*
import org.jetbrains.anko.intentFor
import org.wit.quoteme.R
import org.wit.quoteme.main.MainApp
import org.jetbrains.anko.startActivityForResult
import org.wit.quoteme.models.QuoteMeModel
import android.support.v7.widget.SearchView
import org.wit.quoteme.adapter.CategoryListener
import org.wit.quoteme.adapter.QuoteMeAdapter

class ListCategoriesActivity : AppCompatActivity(),
    CategoryListener {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listcategories)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        loadCategories()

        toolbarMain.title = title
        toolbarMain.setNavigationIcon(R.drawable.quote_bubble_foreground)
        setSupportActionBar(toolbarMain)

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener {
            startActivityForResult<CreateNewCategoryActivity>(0)
        }
    }

    //Try and retract the search bar when category is clicked
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchItem = menu?.findItem(R.id.item_search)
        if(searchItem != null) {
            val searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    showCategories(app.categories.search(newText))
                    return true
                }
            })
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCategoryClick(category: QuoteMeModel) {
        startActivityForResult(intentFor<ListQuotesActivity>().putExtra("category_edit", category),0)
        toolbarMain.collapseActionView()//closes search bar but doesn't open a category as cleanly as I'd like
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadCategories()
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun loadCategories() {
        showCategories(app.categories.findAll())
    }

    fun showCategories(categories: List<QuoteMeModel>){
        recyclerView.adapter =
            QuoteMeAdapter(categories, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }

}
