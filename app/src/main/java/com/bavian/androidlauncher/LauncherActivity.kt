package com.bavian.androidlauncher

import android.app.Activity
import android.os.Bundle

class LauncherActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
    }
}