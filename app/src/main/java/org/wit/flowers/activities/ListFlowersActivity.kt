package org.wit.flowers.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import kotlinx.android.synthetic.main.activity_listflowers.*
import org.jetbrains.anko.intentFor
import org.wit.flowers.R
import org.wit.flowers.main.MainApp
import org.jetbrains.anko.startActivityForResult
import org.wit.flowers.models.FlowerModel
import org.jetbrains.anko.AnkoLogger
import org.wit.flowers.adapter.FlowerAdapter
import org.wit.flowers.adapter.FlowerListener

class ListFlowersActivity : AppCompatActivity(), FlowerListener, AnkoLogger {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listflowers)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        loadFlowers()

        toolbarMain.title = title
        setSupportActionBar(toolbarMain)

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener {
            startActivityForResult<CreateNewFlowerActivity>(0)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        var searchItem = menu?.findItem(R.id.item_search)
        if(searchItem != null) {
            var searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    showFlowers(app.flowers.search(newText))
                    return true
                }
            })
        }
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
            }

            R.id.gallery -> {
                startActivityForResult(intentFor<GalleryActivity>(), 0)
                finish()
            }

            R.id.garden_locations -> {
                startActivityForResult(intentFor<MapsActivity>(), 0)
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onFlowerClick(flower: FlowerModel) {
        startActivityForResult(intentFor<FlowerDetailActivity>().putExtra("flower_edit", flower),0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadFlowers()
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun loadFlowers() {
        showFlowers(app.flowers.findAll())
    }

    fun showFlowers(flowers: List<FlowerModel>){
        recyclerView.adapter =
            FlowerAdapter(flowers, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }

}
