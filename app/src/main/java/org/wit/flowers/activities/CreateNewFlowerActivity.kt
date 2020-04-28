package org.wit.flowers.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_createnewflower.*
import kotlinx.android.synthetic.main.activity_createnewflower.flowerImage
import kotlinx.android.synthetic.main.activity_flower_detail.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import org.wit.flowers.R
import org.wit.flowers.helpers.readImage
import org.wit.flowers.helpers.readImageFromPath
import org.wit.flowers.helpers.showImagePicker
import org.wit.flowers.main.MainApp
import org.wit.flowers.models.FlowerModel

class CreateNewFlowerActivity : AppCompatActivity(), AnkoLogger {

    var flower = FlowerModel()
    lateinit var app : MainApp
    var edit = false
    val IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createnewflower)
        app = application as MainApp

        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)


        if (intent.hasExtra("flower_edit")) {
            edit = true
            flower = intent.extras!!.getParcelable<FlowerModel>("flower_edit")!!
            flowerName.setText(flower.name)
            flowerInformation.setText(flower.information)
            flowerImage.setImageBitmap(readImageFromPath(this, flower.image))
            if (flower.image != null){
                chooseImage.setText(R.string.change_flower_image)
            }
            btnAdd.setText(R.string.save_flower)
        }


        btnAdd.setOnClickListener() {
            flower.name = flowerName.text.toString()
            flower.information = flowerInformation.text.toString()
            if (flower.name.isEmpty()) {
                toast(R.string.enter_flower_name)
            }else{
                if (edit){
                    app.flowers.update(flower.copy())
                }else {
                    app.flowers.create(flower.copy())
                }
                info("add Button Pressed: $flowerName, $flowerInformation")
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            }
        }

        chooseImage.setOnClickListener{
            showImagePicker(this, IMAGE_REQUEST)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_editflower, menu)
        if (edit && menu != null) menu.getItem(0).setVisible(true)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_cancel -> {
                finish()
            }
            R.id.item_delete -> {
                app.flowers.delete(flower)
                startActivityForResult(intentFor<ListFlowersActivity>(),0)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    flower.image = data.getData().toString()
                    flowerImage.setImageBitmap(readImage(this, resultCode, data))
                    chooseImage.setText(R.string.change_flower_image)
                }
            }
        }
    }
}
