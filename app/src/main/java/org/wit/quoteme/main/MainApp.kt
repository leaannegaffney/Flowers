package org.wit.quoteme.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.quoteme.models.QuoteMeMemStore

class MainApp : Application(), AnkoLogger {

    val categories = QuoteMeMemStore()
    override fun onCreate() {
        super.onCreate()
        info("QuoteMe started")
    }
}