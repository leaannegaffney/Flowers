package org.wit.quoteme.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import kotlinx.android.synthetic.main.activity_quotemelist.*
import org.jetbrains.anko.intentFor
import org.wit.quoteme.R
import org.wit.quoteme.main.MainApp
import org.jetbrains.anko.startActivityForResult
import org.wit.quoteme.models.QuoteMeModel
import org.jetbrains.anko.startActivityForResult

class QuoteMeListActivity : AppCompatActivity(), CategoryListener {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotemelist)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = QuoteMeAdapter(app.categories.findAll(), this)

        toolbarMain.title = title
        setSupportActionBar(toolbarMain)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> startActivityForResult<QuoteMeActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCategoryClick(category: QuoteMeModel) {
        startActivityForResult(intentFor<QuoteMeActivity>().putExtra("category_edit", category), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        recyclerView.adapter?.notifyDataSetChanged()
        super.onActivityResult(requestCode, resultCode, data)
    }
}
