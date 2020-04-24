package org.wit.quoteme.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.quoteme.models.*

class MainApp : Application(), AnkoLogger {

    lateinit var categories : QuoteMeStore
     lateinit var quotes : CategoryStore

    override fun onCreate() {
        super.onCreate()
        categories = QuoteMeJSONStore(applicationContext)
        quotes = CategoryMemStore()
        info("QuoteMe started")
    }
}