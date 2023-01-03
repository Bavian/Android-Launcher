package com.bavian.games_list

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.currentRecomposeScope
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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.Dp
import com.bavian.ui.library.ImageButton
import kotlinx.coroutines.launch

@Composable
fun GamesList(
    unfocusedSize: Dp,
    focusedSize: Dp,
    icons: List<ImageBitmap>,
    onClick: (Int) -> Unit,
) {
    val focusRequesters = Array(icons.size) { FocusRequester() }
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    LazyRow(
        modifier = Modifier
            .height(focusedSize)
            .onFocusChanged {
                Log.d("bavavian", "onFocusChanged: $it")
            },
        verticalAlignment = Alignment.CenterVertically,
        state = scrollState,
    ) {
        itemsIndexed(icons) { index, icon ->
            var focused by remember { mutableStateOf(false) }
            val size by animateDpAsState(if (focused) focusedSize else unfocusedSize)
            ImageButton(
                bitmap = icon,
                contentDescription = null,
                modifier = Modifier
                    .focusRequester(focusRequesters[index])
                    .onFocusChanged {
                        focused = it.isFocused
                        if (focused) {
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
    LaunchedEffect(currentRecomposeScope) {
        focusRequesters[0].requestFocus()
    }
}

private suspend inline fun LazyListState.safeScrollToItem(length: Int, index: Int) = scrollToItem(
    when {
        index < 0 -> 0
        index >= length -> length - 1
        else -> index
    }
)
