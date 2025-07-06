package com.example.sonaproject.core.utils

import android.text.Html
import android.text.Spanned
import android.util.Patterns

fun String?.isNullOrEmptyOrBlank(): Boolean = this.isNullOrBlank()

fun String?.orEmptySafe(): String = this ?: ""

fun String.trimmed(): String = this.trim()

fun String.isValidEmail(): Boolean = Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isValidPhone(): Boolean = this.length in 10..15 && this.all { it.isDigit() }

fun String.isValidPassword(minLength: Int = 6): Boolean = this.length >= minLength

fun String.containsOnlyDigits(): Boolean = all { it.isDigit() }

fun String.containsOnlyLetters(): Boolean = all { it.isLetter() }

fun String.capitalizeFirstChar(): String = replaceFirstChar {
    if (it.isLowerCase()) it.titlecase() else it.toString()
}

fun String.capitalizeWords(): String = split(" ").joinToString(" ") {
    it.capitalizeFirstChar()
}

fun String.maskEmail(): String {
    val index = indexOf("@")
    return if (index > 1) {
        "${this.first()}***${this.substring(index - 1)}"
    } else this
}

fun String.maskPhone(): String {
    return if (length >= 10) {
        "${substring(0, 2)}****${substring(length - 4)}"
    } else this
}

fun String.toIntOrZero(): Int = toIntOrNull() ?: 0

fun String.toDoubleOrZero(): Double = toDoubleOrNull() ?: 0.0

fun String.toBooleanSafe(): Boolean = this.equals("true", ignoreCase = true)

fun String.fromHtml(): Spanned = Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)

fun String.formatAsCurrency(symbol: String = "₹"): String = "$symbol$this"

fun String.limit(maxLength: Int): String = if (length <= maxLength)
    this
else
    substring(0, maxLength) + "…"

fun String.onlyDigits(): String = replace(Regex("[^\\d]"), "")

fun String.onlyLetters(): String = replace(Regex("[^A-Za-z]"), "")

