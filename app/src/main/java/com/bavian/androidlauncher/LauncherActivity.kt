package com.bavian.androidlauncher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.core.view.WindowCompat
import com.bavian.androidlauncher.apps.AppsCollector
import org.koin.android.ext.android.inject

class LauncherActivity : ComponentActivity() {

    private val appsCollector: AppsCollector by inject()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContent {
            LazyColumn {
                items(appsCollector.collectApps()) { appData ->
                    Text(text = appData.label.toString())
                }
            }
        }
    }
}