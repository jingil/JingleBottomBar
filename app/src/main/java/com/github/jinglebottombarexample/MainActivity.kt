package com.github.jinglebottombarexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.github.jinglebottombar.JingleBottomBar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val customViews = listOf(
            JingleBottomBar.CustomViewData(R.drawable.music_note, "Music Note"),
            JingleBottomBar.CustomViewData(R.drawable.favorite, "Favorite"),
            JingleBottomBar.CustomViewData(R.drawable.place, "Place"),
                    JingleBottomBar.CustomViewData(R.drawable.music_note, "Album"),
        JingleBottomBar.CustomViewData(R.drawable.place, "Location"),
        JingleBottomBar.CustomViewData(R.drawable.settings, "Settings")
        )
        val myLayout = findViewById<LinearLayout>(R.id.bottom_navigation)

        JingleBottomBar.makeBottomBar(this,myLayout,customViews).show()
    }
}