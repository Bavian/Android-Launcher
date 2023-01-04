@file:Suppress("NOTHING_TO_INLINE")

package com.bavian.games_list

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.core.graphics.drawable.toBitmap
import com.bavian.apps_collector.AppData
import kotlinx.coroutines.launch
import android.view.KeyEvent as NativeKeyEvent

@Composable
fun GamesList(
    unfocusedSize: Dp,
    focusedSize: Dp,
    games: List<AppData>,
    modifier: Modifier,
    onClick: (Int) -> Unit,
    onSelectedGameChange: (AppData) -> Unit,
) {
    var selectedGameIndex by rememberSaveable { mutableStateOf(0) }
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    var parentContainerSize by remember { mutableStateOf(Size.Zero) }
    LazyRow(
        modifier = modifier
            .focusable()
            .onKeyEvent { key ->
                if (key.nativeKeyEvent.action != NativeKeyEvent.ACTION_DOWN) {
                    return@onKeyEvent false
                }

                val newIndex = selectedGameIndex.move(key)
                if (newIndex != selectedGameIndex && newIndex in games.indices) {
                    selectedGameIndex = newIndex
                    return@onKeyEvent true
                }

                return@onKeyEvent false
            }
            .clickable(
                onClick = { onClick(selectedGameIndex) },
            )
            .onGloballyPositioned {
                parentContainerSize = it.parentCoordinates?.size?.toSize() ?: Size.Zero
            },
        verticalAlignment = Alignment.CenterVertically,
        state = scrollState,
    ) {
        item {
            Box(modifier = Modifier.size(unfocusedSize))
        }
        itemsIndexed(games) { index, app ->
            val size by animateDpAsState(if (selectedGameIndex == index) focusedSize else unfocusedSize)
            Image(
                bitmap = app.icon.toBitmap().asImageBitmap(),
                contentDescription = null,
                modifier = Modifier.size(size),
            )
        }
        item {
            Box(modifier = Modifier.width(parentContainerSize.width.dp))
        }
    }
    LaunchedEffect(selectedGameIndex) {
        coroutineScope.launch {
            scrollState.safeScrollToItem(games.size, selectedGameIndex)
        }
        onSelectedGameChange(games[selectedGameIndex])
    }
}

@OptIn(ExperimentalComposeUiApi::class)
private inline fun Int.move(keyEvent: KeyEvent) = this + when (keyEvent.key) {
    Key.DirectionRight -> 1
    Key.DirectionLeft -> -1
    else -> 0
}

private suspend inline fun LazyListState.safeScrollToItem(length: Int, index: Int) = scrollToItem(
    when {
        index < 0 -> 0
        index >= length -> length - 1
        else -> index
    }
)
