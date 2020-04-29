package org.wit.flowers.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_flower_detail.*
import kotlinx.android.synthetic.main.activity_flower_detail.flowerImage
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.intentFor
import org.wit.flowers.R
import org.wit.flowers.helpers.readImageFromPath
import org.wit.flowers.models.FlowerModel

class FlowerDetailActivity: AppCompatActivity(), AnkoLogger {

    var flower = FlowerModel()


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_flower_detail)

            flower = intent.extras!!.getParcelable<FlowerModel>("flower_edit")!!
            toolbarFlowerDetail.title = flower.name
            setSupportActionBar(toolbarFlowerDetail)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            flowerImage.setImageBitmap(readImageFromPath(this, flower.image))
            flowerNameDetail.setText(flower.name)
            flowerInformationDetail.setText(flower.information)


        }


        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.menu_flower, menu)
            return super.onCreateOptionsMenu(menu)
        }

        override fun onOptionsItemSelected(item: MenuItem?): Boolean {
            when (item?.itemId) {
                android.R.id.home -> {
                    startActivityForResult(intentFor<ListFlowersActivity>(), 0)
                    finish()
                }

                R.id.item_edit -> {
                    startActivityForResult(intentFor<CreateNewFlowerActivity>().putExtra("flower_edit", flower), 0)
                    finish()
                }

            }
            return super.onOptionsItemSelected(item)
        }

    }