package com.bavian.androidlauncher.apps

import android.graphics.drawable.Drawable

data class AppData(
    val label: CharSequence,
    val packageName: CharSequence,
    val icon: Drawable,
)
