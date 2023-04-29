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
            JingleBottomBar.CustomViewData(android.R.drawable.gallery_thumb, "Text 1"),
            JingleBottomBar.CustomViewData(android.R.drawable.btn_minus, "Text 2"),
            JingleBottomBar.CustomViewData(android.R.drawable.ic_menu_camera, "Text 3"),
                    JingleBottomBar.CustomViewData(android.R.drawable.ic_menu_crop, "Text 1"),
        JingleBottomBar.CustomViewData(android.R.drawable.status_bar_item_app_background, "Text 2"),
        JingleBottomBar.CustomViewData(android.R.drawable.sym_call_incoming, "Text 3")
        )
        val myLayout = findViewById<LinearLayout>(R.id.bottom_navigation)

        JingleBottomBar.makeBottomBar(this,myLayout,customViews).show()
    }
}