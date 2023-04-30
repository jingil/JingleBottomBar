package com.github.jinglebottombar

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.children

class JingleBottomBar(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    class makeBottomBar {
        private var context: Context
        private var view: ViewGroup
        private var customViewData: List<CustomViewData>


        constructor(context: Context, view: ViewGroup, customViewData: List<CustomViewData>) {
            this.context = context
            this.view = view
            this.customViewData = customViewData

        }

        fun show() {

            addCustomViewsLinearLayout(
                context,
                view,
                customViewData,
                48,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                15
            )
        }


        fun addCustomViewsLinearLayout(
            context: Context,
            parentView: ViewGroup,
            customViews: List<CustomViewData>,
            iconSize: Int,
            height: Int,
            margin: Int
        ) {
            val linearLayout = LinearLayout(context).apply {
                orientation = LinearLayout.HORIZONTAL
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                setBackgroundColor(context.resources.getColor(R.color.teal_700))
            }

            val textViewsList = mutableListOf<TextView>()

            for (customView in customViews) {
                val imageView = ImageView(context).apply {
                    setImageResource(customView.imageResource)
                    val sizeInPixels = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        iconSize.toFloat(),
                        context.resources.displayMetrics
                    ).toInt()
                    layoutParams = LinearLayout.LayoutParams(sizeInPixels, sizeInPixels)
                    val imageViewPadding = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        10f,
                        context.resources.displayMetrics
                    ).toInt()
                    setPadding(imageViewPadding, 0, imageViewPadding/2, 0)
                }

                val textView = TextView(context).apply {
                    text = customView.text
                    visibility = View.GONE
                    gravity = Gravity.CENTER
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    val textVIewPadding = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        20f,
                        context.resources.displayMetrics
                    ).toInt()
                    setPadding(textVIewPadding/2, 0, textVIewPadding, 0)
                    setTextColor(context.resources.getColor(R.color.white))
                }

                val customViewLayout = LinearLayout(context).apply {
                    orientation = LinearLayout.HORIZONTAL
                    addView(imageView)
                    addView(textView)
                    gravity = Gravity.CENTER
                    val padding = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        8f,
                        context.resources.displayMetrics
                    ).toInt()
                    setPadding(padding, padding, padding, padding)
                    val customViewLayoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    val sizeInPixelsMargin = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        margin.toFloat(),
                        context.resources.displayMetrics
                    ).toInt()
                    customViewLayoutParams.setMargins(sizeInPixelsMargin, sizeInPixelsMargin, sizeInPixelsMargin, sizeInPixelsMargin)
                    layoutParams = customViewLayoutParams
                }

                linearLayout.addView(customViewLayout)
                textViewsList.add(textView)

                customViewLayout.setOnClickListener {
                    for (textView in textViewsList) {
                        textView.visibility = View.GONE
                    }
                    textView.visibility = View.VISIBLE
                    customViewLayout.background =
                        ContextCompat.getDrawable(context, R.drawable.rectangle_transparent)
                    for (view in linearLayout.children) {
                        if (view != customViewLayout) {
                            view.setBackgroundColor(Color.TRANSPARENT)
                        }
                    }
                }
            }

            val horizontalScrollView = HorizontalScrollView(context).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                addView(linearLayout)
                isHorizontalScrollBarEnabled = false
                isVerticalScrollBarEnabled = false
            }

            parentView.addView(horizontalScrollView)
        }



    }

    data class CustomViewData(
        val imageResource: Int,
        val text: String
    )

}



