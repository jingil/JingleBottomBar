package com.github.jinglebottombar.view

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
import com.github.jinglebottombar.R
import com.github.jinglebottombar.model.CustomViewData

class JingleBottomBar(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    class JingleBottomBarLibrary {
        companion object {
            fun createBottomBar(
                context: Context,
                parentView: ViewGroup,
                viewItems: List<CustomViewData>,
                imageSize: Int = 64,
                backgroundColor: Int = Color.BLACK,
                textColor: Int = Color.WHITE,
                itemViewMargins: List<Int> = listOf(15, 15, 5, 15),
                imageViewPaddings: List<Int> = listOf(15, 0, 15, 0),
                textViewPaddings: List<Int> = listOf(15, 0, 20, 0),
                itemViewPaddings: List<Int> = listOf(10, 0, 10, 0)


            ) {
                val linearLayout = LinearLayout(context).apply {
                    orientation = LinearLayout.HORIZONTAL
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LayoutParams.WRAP_CONTENT
                    )
                }

                val textViewsList = mutableListOf<TextView>()
                val horizontalScrollView = HorizontalScrollView(context).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                    )
                    addView(linearLayout)
                    setBackgroundColor(backgroundColor)
                    isHorizontalScrollBarEnabled = false
                    isVerticalScrollBarEnabled = false
                }

                for (customView in viewItems) {

                    //Imageview
                    val imageView = ImageView(context).apply {
                        setImageResource(customView.imageResource)

                        val sizeInPixels = TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP,
                            imageSize.toFloat(),
                            context.resources.displayMetrics
                        ).toInt()

                        layoutParams = LinearLayout.LayoutParams(sizeInPixels, sizeInPixels)

                        val leftImageViewPadding = convertToPixels(context, imageViewPaddings[0])
                        val topImageViewPadding = convertToPixels(context, imageViewPaddings[1])
                        val rightImageViewPadding = convertToPixels(context, imageViewPaddings[2])
                        val bottomImageViewPadding = convertToPixels(context, imageViewPaddings[3])

                        setPadding(
                            leftImageViewPadding,
                            topImageViewPadding,
                            rightImageViewPadding,
                            bottomImageViewPadding
                        )

                    }

                    val textView = TextView(context).apply {
                        text = customView.text
                        visibility = View.GONE
                        gravity = Gravity.CENTER
                        textAlignment = View.TEXT_ALIGNMENT_CENTER

                        setTextColor(textColor)

                        val lefttextViewPadding = convertToPixels(context, textViewPaddings[0])
                        val toptextViewPadding = convertToPixels(context, textViewPaddings[1])
                        val righttextViewPadding = convertToPixels(context, textViewPaddings[2])
                        val bottomtextViewPadding = convertToPixels(context, textViewPaddings[3])
                        setPadding(
                            lefttextViewPadding,
                            toptextViewPadding,
                            righttextViewPadding,
                            bottomtextViewPadding
                        )
                    }

                    val customViewLayout = LinearLayout(context).apply {
                        orientation = LinearLayout.HORIZONTAL
                        addView(imageView)
                        addView(textView)
                        gravity = Gravity.CENTER
                        val leftItemViewPadding = convertToPixels(context, itemViewPaddings[0])
                        val topItemViewPadding = convertToPixels(context, itemViewPaddings[1])
                        val rightItemViewPadding = convertToPixels(context, itemViewPaddings[2])
                        val bottomItemViewPadding = convertToPixels(context, itemViewPaddings[3])
                        setPadding(
                            leftItemViewPadding,
                            topItemViewPadding,
                            rightItemViewPadding,
                            bottomItemViewPadding
                        )
                        val customViewLayoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )

                        val leftItemViewMargin = convertToPixels(context, itemViewMargins[0])
                        val topItemViewMargin = convertToPixels(context, itemViewMargins[1])
                        val rightItemViewMargin = convertToPixels(context, itemViewMargins[2])
                        val bottomItemViewMargin = convertToPixels(context, itemViewMargins[3])


                        customViewLayoutParams.setMargins(
                            leftItemViewMargin,
                            topItemViewMargin,
                            rightItemViewMargin,
                            bottomItemViewMargin
                        )
                        layoutParams = customViewLayoutParams
                    }

                    linearLayout.addView(customViewLayout)
                    textViewsList.add(textView)

                    customViewLayout.setOnClickListener {

                        for (textView in textViewsList) {
                            textView.alpha = 0f
                            textView.translationX = -50f
                            textView.visibility = View.GONE
                        }

                        textView.alpha = 1f
                        textView.translationX = 0f
                        textView.visibility = View.VISIBLE
                        customViewLayout.background =
                            ContextCompat.getDrawable(context, R.drawable.rectangle_transparent)
                        imageView.translationX = -10f
                        for (view in linearLayout.children) {
                            if (view != customViewLayout) {
                                view.setBackgroundColor(Color.TRANSPARENT)
                            }
                        }
                        val scrollX =
                            customViewLayout.left - horizontalScrollView.width / 2 + customViewLayout.width / 2
                        horizontalScrollView.smoothScrollTo(scrollX, 0)
                    }
                }

                parentView.addView(horizontalScrollView)
            }

            private fun convertToPixels(context: Context, i: Int): Int {
                val pixel = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    i.toFloat(),
                    context.resources.displayMetrics
                ).toInt()
                return pixel;
            }


        }

    }



}



