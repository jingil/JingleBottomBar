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
                100,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                5
            );
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
            }
            linearLayout.setBackgroundColor(context.resources.getColor(R.color.teal_700))
            val textViewsList = mutableListOf<TextView>()
            for (customView in customViews) {
                val imageView = ImageView(context)
                imageView.setImageResource(customView.imageResource)
                val sizeInPixels = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    iconSize.toFloat(),
                    context.resources.displayMetrics
                ).toInt()
                imageView.layoutParams = LinearLayout.LayoutParams(sizeInPixels, sizeInPixels)
                imageView.setPadding(0, 8, 0, 8)
                //   imageView.background = context.getDrawable(R.drawable.ic_launcher_background)

                val textView = TextView(context)
                textView.text = customView.text
                textView.visibility = View.GONE
                textView.gravity = Gravity.CENTER
                textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
                textView.setPadding(0, 16, 0, 16)
                textView.setTextColor(context.resources.getColor(R.color.black))


                val customViewLayout = LinearLayout(context)
                customViewLayout.orientation = LinearLayout.HORIZONTAL
                customViewLayout.addView(imageView)
                customViewLayout.addView(textView)


// Set padding for the custom view layout
                val padding = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    8f,
                    context.resources.displayMetrics
                ).toInt()
                customViewLayout.setPadding(padding, padding, padding, padding)

                val customViewLayoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                customViewLayoutParams.setMargins(margin, margin, margin, margin)
                customViewLayout.layoutParams = customViewLayoutParams

                linearLayout.addView(customViewLayout)

                // Add the textView to the list so we can hide it later
                textViewsList.add(textView)

                // Set the click listener on the imageView to show the corresponding textView and hide all others
                imageView.setOnClickListener {
                    for (textView in textViewsList) {
                        textView.visibility = View.GONE
                    }
                    textView.visibility = View.VISIBLE

                    // Set background color for clicked custom view
                    //  customViewLayout.setBackgroundColor(context.resources.getColor(R.color.purple_500))
                    customViewLayout.background =
                        ContextCompat.getDrawable(context, R.drawable.rectangle_transparent)
                    // Remove background color for other custom views
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



