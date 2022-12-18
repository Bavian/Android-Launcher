package com.bavian.androidlauncher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.WindowCompat
import com.bavian.androidlauncher.apps.AppData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LauncherActivity : ComponentActivity() {

    private val launcherViewModel: LauncherViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContent {
            val scope = rememberCoroutineScope()
            val gamesListState = rememberLazyListState()
            val gamesList by launcherViewModel.gamesList.collectAsState()
            val appsList by launcherViewModel.appsList.collectAsState()
            Column(modifier = Modifier.fillMaxSize()) {
                LazyRow(
                    state = gamesListState,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    itemsIndexed(gamesList) { index, item ->
                        GameIcon(scope, gamesListState, index, item) {
                            launcherViewModel.appClicked(this@LauncherActivity, item)
                        }
                    }
//                    Debug hack
//                    itemsIndexed(appsList) { index, item ->
//                        GameIcon(scope, gamesListState, index + gamesList.size, item) {
//                            launcherViewModel.appClicked(this@LauncherActivity, item)
//                        }
//                    }
                }
                LazyColumn(
                    contentPadding = PaddingValues(24.dp, 24.dp, 24.dp, 0.dp),
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    items(appsList) {
                        LauncherButton(it) {
                            launcherViewModel.appClicked(this@LauncherActivity, it)
                        }
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        launcherViewModel.onStart()
    }

}

@Composable
private fun GameIcon(
    scope: CoroutineScope,
    scrollState: LazyListState,
    index: Int,
    appData: AppData,
    onClick: () -> Unit,
) {
    var size by remember { mutableStateOf(48) }
    TextButton (
        onClick = onClick,
        modifier = Modifier
            .onFocusChanged {
                if (it.isFocused) {
                    size = 72
                    val scrollDestination = if (index == 0) 0 else index - 1
                    scope.launch { scrollState.scrollToItem(scrollDestination) }
                } else {
                    size = 48
                }
            }
            .focusable()
    ) {
        Icon(
            bitmap = appData.icon.toBitmap().asImageBitmap(),
            contentDescription = "",
            tint = Color.Unspecified,
            modifier = Modifier.size(size.dp),
        )
    }
}

@Composable
private fun LauncherButton(appData: AppData, onClick: () -> Unit) {
    TextButton(onClick = onClick) {
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