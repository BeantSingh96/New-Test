package com.example.sonaproject.core.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat


fun Context.color(color: Int): Int {
    return ContextCompat.getColor(this, color)
}

fun Context.drawable(drawable: Int): Drawable? {
    return ContextCompat.getDrawable(this, drawable)
}
