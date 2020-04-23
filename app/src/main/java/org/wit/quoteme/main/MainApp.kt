package org.wit.quoteme.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.quoteme.models.QuoteMeJSONStore
import org.wit.quoteme.models.QuoteMeStore

class MainApp : Application(), AnkoLogger {

    lateinit var categories : QuoteMeStore
    override fun onCreate() {
        super.onCreate()
        categories = QuoteMeJSONStore(applicationContext)
        info("QuoteMe started")
    }
}