package com.bavian.androidlauncher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.bavian.app_bar.AppBar
import com.bavian.app_bar.AppBarItemParams
import com.bavian.games_list.GamesList
import org.koin.androidx.viewmodel.ext.android.viewModel

class LauncherActivity : ComponentActivity() {

    private val launcherViewModel: LauncherViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContent {
            val gamesList by launcherViewModel.gamesList.collectAsState()
            val appsList by launcherViewModel.appsList.collectAsState()
            val choseAppState = remember { mutableStateOf(gamesList.firstOrNull()) }
            Box(modifier = Modifier
                .fillMaxSize()
            ) {
                val games = gamesList + appsList
                val gamesFocusRequester = remember { FocusRequester() }
                GamesList(
                    unfocusedSize = 48.dp,
                    focusedSize = 96.dp,
                    icons = games,
                    modifier = Modifier
                        .focusRequester(gamesFocusRequester)
                        .height(96.dp),
                    onClick = {
                        launcherViewModel.appClicked(this@LauncherActivity, games[it])
                    },
                )
                LaunchedEffect(currentRecomposeScope) {
                    gamesFocusRequester.requestFocus()
                }
                Text(
                    text = choseAppState.value?.label?.toString() ?: "",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )
                AppBar(
                    apps = appsList.slice(0..3),
                    appsParams = AppBarItemParams(
                        size = 48.dp,
                        focusedColor = MaterialTheme.colorScheme.primary,
                    ),
                    modifier = Modifier.align(Alignment.BottomCenter)
                        .background(
                            color = MaterialTheme.colorScheme.background,
                            shape = RoundedCornerShape(35),
                        )
                        .clip(RoundedCornerShape(35))
                )
            }
        }
    }

    override fun onStart() {
        super.onStart()
        launcherViewModel.onStart()
    }

}
