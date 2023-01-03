package com.bavian.app_bar

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap

@Composable
fun AppBar(
    apps: List<ImageBitmap>,
    appsParams: AppBarItemParams,
    modifier: Modifier,
) = Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier
) {
    apps.forEach { AppBarItem(bitmap = it, appsParams = appsParams) }
    OthersItem(appsParams)
}

@Composable
private fun AppBarItem(
    bitmap: ImageBitmap,
    appsParams: AppBarItemParams,
) {
    var focused by remember { mutableStateOf(false) }
    val color by animateColorAsState(if (focused) appsParams.focusedColor else Color.Transparent)
    TextButton(
        onClick = {},
        modifier = Modifier
            .background(color)
            .onFocusChanged { focused = it.isFocused }
            .focusable(),
    ) {
        Image(
            bitmap = bitmap,
            contentDescription = "",
            modifier = Modifier.size(appsParams.size),
        )
    }
}

@Composable
private fun OthersItem(appsParams: AppBarItemParams) {
    var focused by remember { mutableStateOf(false) }
    val color by animateColorAsState(if (focused) appsParams.focusedColor else Color.Transparent)
    TextButton(
        onClick = {},
        modifier = Modifier
            .background(color)
            .onFocusChanged { focused = it.isFocused }
            .focusable(),
    ) {
        Image(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = "",
            modifier = Modifier.size(appsParams.size),
        )
    }
}
