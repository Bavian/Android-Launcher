package com.bavian.androidlauncher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.WindowCompat
import com.bavian.androidlauncher.apps.AppData
import org.koin.androidx.viewmodel.ext.android.viewModel

class LauncherActivity : ComponentActivity() {

    private val launcherViewModel: LauncherViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContent {
            val appsList by launcherViewModel.appsList.collectAsState(emptyList())
            LazyColumn(
                contentPadding = PaddingValues(24.dp, 24.dp, 24.dp, 0.dp),
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                items(appsList) { LauncherButton(it) }
            }
        }
    }

    @Composable
    private fun LauncherButton(appData: AppData) {
        TextButton(
            onClick = { launcherViewModel.appClicked(this, appData) },
        ) {
            Icon(
                bitmap = appData.icon.toBitmap().asImageBitmap(),
                contentDescription = "",
                tint = Color.Unspecified,
                modifier = Modifier.width(48.dp)
            )
            Text(
                text = appData.label.toString(),
                modifier = Modifier
                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                    .background(color = Color.White)
                    .padding(5.dp, 0.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            )
        }
    }
}
