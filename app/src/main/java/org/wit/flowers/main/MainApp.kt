package org.wit.flowers.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.flowers.models.*

class MainApp : Application(), AnkoLogger {

    lateinit var flowers : FlowerStore

    override fun onCreate() {
        super.onCreate()
        flowers = FlowerJSONStore(applicationContext)
        info("Buncha Flowers started")
    }
}