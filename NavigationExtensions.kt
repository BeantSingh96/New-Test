package com.example.sonaproject.core.utils

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.sonaproject.R

fun NavController.navigateWithAnimations(
    destId: Int,
    args: Bundle? = null,
    navOptions: NavOptions? = null
) {
    val defaultNavOptions = NavOptions.Builder()
        .setEnterAnim(R.anim.slide_in_right)
        .setExitAnim(R.anim.slide_out_left)
        .setPopEnterAnim(R.anim.fade_in)
        .setPopExitAnim(R.anim.fade_out)
        .build()

    this.navigate(
        destId,
        args,
        navOptions ?: defaultNavOptions
    )
}