package com.github.jinglebottombarexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.github.jinglebottombar.JingleBottomBar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewItems = listOf(
            JingleBottomBar.CustomViewData(R.drawable.music_note, "Music Note"),
            JingleBottomBar.CustomViewData(R.drawable.favorite, "Favorite"),
           /* JingleBottomBar.CustomViewData(R.drawable.place, "Place"),
                    JingleBottomBar.CustomViewData(R.drawable.music_note, "Album"),
        JingleBottomBar.CustomViewData(R.drawable.place, "Location"),*/
        JingleBottomBar.CustomViewData(R.drawable.settings, "Settings")
        )
        val myLayout = findViewById<LinearLayout>(R.id.bottom_navigation)


        val imageViewMargins = listOf(15, 10, 20, 5) // left, top, right, bottom
        val textViewMargins = listOf(15, 10, 20, 5)
        val itemViewMargins = listOf(15, 10, 20, 5)

        val imageViewPaddings = listOf(15, 10, 20, 5) // left, top, right, bottom
        val textViewPaddings = listOf(15, 10, 20, 5)
        val itemViewPaddings = listOf(15, 10, 20, 5)

        val bottomBarView = JingleBottomBar.JingleBottomBarLibrary.createBottomBar(
            context = applicationContext,
            parentView = myLayout,
            viewItems = viewItems,
            imageSize = 64,
            backgroundColor = ContextCompat.getColor(applicationContext, R.color.teal_700),
            textColor =  ContextCompat.getColor(applicationContext, R.color.white),
            itemViewMargins = itemViewMargins,
            imageViewPaddings = imageViewPaddings,
            textViewPaddings = textViewPaddings,
            itemViewPaddings = itemViewPaddings
        )
    }
}