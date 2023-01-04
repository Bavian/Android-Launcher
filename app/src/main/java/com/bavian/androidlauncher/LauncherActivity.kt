package com.bavian.androidlauncher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bavian.app_bar.AppBar
import com.bavian.app_bar.AppBarItemParams
import com.bavian.games_list.GamesList
import org.koin.androidx.viewmodel.ext.android.viewModel

private val GAMES_LIST_HEIGHT = 96.dp

private const val DESTINATION_MAIN_SCREEN = "mainScreen"
private const val DESTINATION_ALL_APPS = "allApps"

class LauncherActivity : ComponentActivity() {

    private val launcherViewModel: LauncherViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = DESTINATION_MAIN_SCREEN) {
                composable(DESTINATION_MAIN_SCREEN) {
                    val gamesList by launcherViewModel.gamesList.collectAsState()
                    val appsList by launcherViewModel.appsList.collectAsState()
                    val gamesStub = gamesList + appsList
                    var selectedGame by remember { mutableStateOf(gamesStub.firstOrNull()) }
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        val gamesFocusRequester = remember { FocusRequester() }
                        GamesList(
                            unfocusedSize = 48.dp,
                            focusedSize = GAMES_LIST_HEIGHT,
                            games = gamesStub,
                            modifier = Modifier
                                .focusRequester(gamesFocusRequester)
                                .height(GAMES_LIST_HEIGHT),
                            onSelectedGameChange = {
                                selectedGame = it
                            },
                            onClick = {
                                launcherViewModel.appClicked(this@LauncherActivity, gamesStub[it])
                            },
                        )
                        LaunchedEffect(currentRecomposeScope) {
                            gamesFocusRequester.requestFocus()
                        }
                        Text(
                            text = selectedGame?.label?.toString() ?: "",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.TopCenter)
                                .padding(0.dp, GAMES_LIST_HEIGHT, 0.dp, 0.dp),
                        )
                        AppBar(
                            apps = appsList.slice(0..3),
                            appsParams = AppBarItemParams(
                                size = 48.dp,
                                focusedColor = MaterialTheme.colorScheme.primary,
                            ),
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .background(
                                    color = MaterialTheme.colorScheme.background,
                                    shape = RoundedCornerShape(35),
                                )
                                .clip(RoundedCornerShape(35)),
                            onOtherClick = { navController.navigate(DESTINATION_ALL_APPS) }
                        )
                    }
                }
                composable(DESTINATION_ALL_APPS) {
                    Text("Hello_world!")
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        launcherViewModel.onStart()
    }

}
