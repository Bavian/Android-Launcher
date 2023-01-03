package com.bavian.app_bar

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.graphics.drawable.toBitmap
import com.bavian.apps_collector.AppData
import com.bavian.ui.library.ImageButton

@Composable
fun AppBar(
    apps: List<AppData>,
    appsParams: AppBarItemParams,
    modifier: Modifier,
) = Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier
) {
    apps.forEach { AppBarItem(app = it, appsParams = appsParams) }
    OthersItem(appsParams)
}

@Composable
private fun AppBarItem(
    app: AppData,
    appsParams: AppBarItemParams,
) {
    var focused by remember { mutableStateOf(false) }
    val color by animateColorAsState(if (focused) appsParams.focusedColor else Color.Transparent)
    ImageButton(
        bitmap = app.icon.toBitmap().asImageBitmap(),
        contentDescription = null,
        modifier = Modifier
            .background(color)
            .onFocusChanged { focused = it.isFocused }
            .focusable()
            .size(appsParams.size),
        onClick = { }
    )
}

@Composable
private fun OthersItem(appsParams: AppBarItemParams) {
    var focused by remember { mutableStateOf(false) }
    val color by animateColorAsState(if (focused) appsParams.focusedColor else Color.Transparent)
    ImageButton(
        imageVector = Icons.Filled.MoreVert,
        contentDescription = null,
        modifier = Modifier
            .background(color)
            .onFocusChanged { focused = it.isFocused }
            .focusable()
            .size(appsParams.size),
        onClick = { }
    )
}
