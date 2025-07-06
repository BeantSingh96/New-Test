package com.example.sonaproject.core.utils

import android.content.Context
import android.util.TypedValue
import android.view.View

fun Context.dpToPx(dp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)
}

fun Context.dpToPxInt(dp: Float): Int {
    return dpToPx(dp).toInt()
}

fun Context.pxToDp(px: Float): Float {
    return px / resources.displayMetrics.density
}

fun View.setWidthDp(dp: Float) {
    layoutParams.width = context.dpToPxInt(dp)
    requestLayout()
}

fun View.setHeightDp(dp: Float) {
    layoutParams.height = context.dpToPxInt(dp)
    requestLayout()
}

fun Context.spToPx(sp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, resources.displayMetrics)
}

val Context.screenWidthDp: Float
    get() = resources.displayMetrics.widthPixels / resources.displayMetrics.density

val Context.screenHeightDp: Float
    get() = resources.displayMetrics.heightPixels / resources.displayMetrics.density

fun Context.dpForScreenSize(
    small: Float,    // e.g., for phones
    medium: Float,   // e.g., for tablets
    large: Float     // e.g., for TVs
): Float {
    return when {
        screenWidthDp < 600 -> small
        screenWidthDp < 840 -> medium
        else -> large
    }
}