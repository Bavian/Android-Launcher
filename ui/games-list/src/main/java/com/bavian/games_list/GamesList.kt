package com.bavian.games_list

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.Dp
import androidx.core.graphics.drawable.toBitmap
import com.bavian.apps_collector.AppData
import com.bavian.ui.library.ImageButton
import kotlinx.coroutines.launch

@Composable
fun GamesList(
    unfocusedSize: Dp,
    focusedSize: Dp,
    icons: List<AppData>,
    modifier: Modifier,
    onClick: (Int) -> Unit,
) {
    var selectedGameIndex by remember { mutableStateOf(0) }
    val gamesFocusRequesters = Array(icons.size) { FocusRequester() }
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    LazyRow(
        modifier = modifier
            .onFocusChanged {
                if (it.isFocused) {
                    gamesFocusRequesters[selectedGameIndex].requestFocus()
                }
            }
            .focusable(),
        verticalAlignment = Alignment.CenterVertically,
        state = scrollState,
    ) {
        itemsIndexed(icons) { index, app ->
            val size by animateDpAsState(if (selectedGameIndex == index) focusedSize else unfocusedSize)
            ImageButton(
                bitmap = app.icon.toBitmap().asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .focusRequester(gamesFocusRequesters[index])
                    .onFocusChanged {
                        if (it.isFocused) {
                            selectedGameIndex = index
                            coroutineScope.launch {
                                scrollState.safeScrollToItem(icons.size, index - 1)
                            }
                        }
                    }
                    .focusable()
                    .size(size),
                onClick = { onClick(index) },
            )
        }
    }
}

private suspend inline fun LazyListState.safeScrollToItem(length: Int, index: Int) = scrollToItem(
    when {
        index < 0 -> 0
        index >= length -> length - 1
        else -> index
    }
)
