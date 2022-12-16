package com.bavian.androidlauncher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.WindowCompat
import com.bavian.androidlauncher.apps.AppsCollector
import org.koin.android.ext.android.inject

class LauncherActivity : ComponentActivity() {

    private val appsCollector: AppsCollector by inject()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContent {
            LazyColumn(
                contentPadding = PaddingValues(24.dp, 24.dp, 24.dp, 0.dp),
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                items(appsCollector.collectApps()) { appData ->
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Icon(
                            bitmap = appData.icon.toBitmap().asImageBitmap(),
                            contentDescription = "",
                            tint = Color.Unspecified,
                            modifier = Modifier.width(48.dp)
                        )
                        Text(
                            text = appData.label.toString(),
                            modifier = Modifier.background(color = Color.White)
                                .fillMaxWidth()
                                .align(Alignment.CenterVertically)
                        )
                    }
                }
            }
        }
    }
}