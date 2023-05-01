package com.github.jinglebottombarexample

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.github.jinglebottombar.model.CustomViewData
import com.github.jinglebottombar.view.JingleBottomBar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewItems = listOf(
            CustomViewData(R.drawable.music_note, "Music Note"),
            CustomViewData(R.drawable.favorite, "Favorite"),
            CustomViewData(R.drawable.place, "Place"),
            CustomViewData(R.drawable.music_note, "Album"),
            CustomViewData(R.drawable.place, "Location"),
            CustomViewData(R.drawable.settings, "Settings")
        )
        val myLayout = findViewById<LinearLayout>(R.id.bottom_navigation)

        val itemViewMargins = listOf(15, 15, 5, 15)
        val imageViewPaddings = listOf(15, 0, 15, 0) // left, top, right, bottom
        val textViewPaddings = listOf(15, 0, 20, 0)
        val itemViewPaddings = listOf(10, 0, 10, 0)

        /*   val bottomBarView = JingleBottomBar.JingleBottomBarLibrary.createBottomBar(
               context = applicationContext,
               parentView = myLayout,
               viewItems = viewItems,

           )*/


        val bottomBarView1 = JingleBottomBar.JingleBottomBarLibrary.createBottomBar(
            context = applicationContext,
            parentView = myLayout,
            viewItems = viewItems,
            imageSize = 64,
            backgroundColor = ContextCompat.getColor(applicationContext, R.color.teal_700),
            textColor = ContextCompat.getColor(applicationContext, R.color.white),
            itemViewMargins = itemViewMargins,
            imageViewPaddings = imageViewPaddings,
            textViewPaddings = textViewPaddings,
            itemViewPaddings = itemViewPaddings
        )
    }
}