package org.wit.quoteme.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_addimagetocategory.*
import org.jetbrains.anko.AnkoLogger
import org.wit.quoteme.R
import org.wit.quoteme.main.MainApp

class AddImageToCategoryActivity : AppCompatActivity(), AnkoLogger{

    lateinit var app : MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addimagetocategory)

        toolbarCategory.title = title
        toolbarCategory.setNavigationIcon(R.drawable.quote_bubble_foreground)
        setSupportActionBar(toolbarCategory)
    }
}