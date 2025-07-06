package com.example.sonaproject.core.utils

import android.content.Context
import android.os.SystemClock
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView


fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.isGone(): Boolean = visibility == View.GONE

fun View.showIf(condition: Boolean) {
    visibility = if (condition) View.VISIBLE else View.GONE
}

fun View.setSafeClickListener(interval: Long = 500L, onClick: (View) -> Unit) {
    var lastClickTime = 0L
    setOnClickListener {
        if (SystemClock.elapsedRealtime() - lastClickTime >= interval) {
            lastClickTime = SystemClock.elapsedRealtime()
            onClick(it)
        }
    }
}

fun View.enable() {
    isEnabled = true
    alpha = 1f
}

fun View.disable() {
    isEnabled = false
    alpha = 0.5f
}

fun TextView.setTextOrHide(text: String?) {
    if (!text.isNullOrEmpty()) {
        visible()
        this.text = text
    } else {
        gone()
    }
}

fun EditText.getTrimmedText(): String = text.toString().trim()

fun View.setMargins(
    left: Int = marginLeft,
    top: Int = marginTop,
    right: Int = marginRight,
    bottom: Int = marginBottom
) {
    val params = layoutParams as? ViewGroup.MarginLayoutParams
    params?.setMargins(left, top, right, bottom)
    layoutParams = params
}

val View.marginLeft get() = (layoutParams as? ViewGroup.MarginLayoutParams)?.leftMargin ?: 0
val View.marginTop get() = (layoutParams as? ViewGroup.MarginLayoutParams)?.topMargin ?: 0
val View.marginRight get() = (layoutParams as? ViewGroup.MarginLayoutParams)?.rightMargin ?: 0
val View.marginBottom get() = (layoutParams as? ViewGroup.MarginLayoutParams)?.bottomMargin ?: 0

fun View.showKeyboard() {
    requestFocus()
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun View.fadeIn(duration: Long = 300) {
    animate().alpha(1f).setDuration(duration).withStartAction { visible() }.start()
}

fun View.fadeOut(duration: Long = 300) {
    animate().alpha(0f).setDuration(duration).withEndAction { gone() }.start()
}