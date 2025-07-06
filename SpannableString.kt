package com.example.sonaproject.core.utils

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.AbsoluteSizeSpan
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat


object SpannableUtils {

    data class StyledText(
        val text: String,
        @ColorRes val colorRes: Int? = null,
        val sizeInSp: Float? = null,
        val isBold: Boolean = false,
        val isItalic: Boolean = false,
        val onClick: (() -> Unit)? = null
    )

    fun applyStyledText(
        context: Context,
        textView: TextView,
        parts: List<StyledText>
    ) {
        val spannable = SpannableStringBuilder()
        var startIndex = 0

        parts.forEach { part ->
            spannable.append(part.text)
            val endIndex = startIndex + part.text.length

            part.colorRes?.let { colorRes ->
                val color = ContextCompat.getColor(context, colorRes)
                spannable.setSpan(
                    ForegroundColorSpan(color),
                    startIndex,
                    endIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }

            part.sizeInSp?.let { size ->
                spannable.setSpan(
                    AbsoluteSizeSpan(size.toInt(), true),
                    startIndex,
                    endIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }

            when {
                part.isBold && part.isItalic -> {
                    spannable.setSpan(
                        StyleSpan(Typeface.BOLD_ITALIC),
                        startIndex,
                        endIndex,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }

                part.isBold -> {
                    spannable.setSpan(
                        StyleSpan(Typeface.BOLD),
                        startIndex,
                        endIndex,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }

                part.isItalic -> {
                    spannable.setSpan(
                        StyleSpan(Typeface.ITALIC),
                        startIndex,
                        endIndex,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
            }

            part.onClick?.let { onClick ->
                spannable.setSpan(
                    object : ClickableSpan() {
                        override fun onClick(widget: View) = onClick()
                        override fun updateDrawState(ds: TextPaint) {
                            ds.isUnderlineText = false
                        }
                    },
                    startIndex,
                    endIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }

            startIndex = endIndex
        }

        textView.text = spannable
        textView.movementMethod = LinkMovementMethod.getInstance()
        textView.linksClickable = true
        textView.highlightColor = Color.TRANSPARENT
    }
}