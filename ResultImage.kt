package com.example.sonaproject.imagePicker

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher

interface ResultImage {
    val result: ActivityResultLauncher<Intent>
}