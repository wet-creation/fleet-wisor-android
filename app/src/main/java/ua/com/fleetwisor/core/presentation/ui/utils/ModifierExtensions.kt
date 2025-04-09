package ua.com.fleetwisor.core.presentation.ui.utils

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    this.clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}

@Composable
fun Modifier.underline(color: Color): Modifier {
    return this.drawBehind {
        val strokeWidthPx = 1.dp.toPx()
        val verticalOffset = size.height - 2.sp.toPx()
        drawLine(
            color = color,
            strokeWidth = strokeWidthPx,
            start = Offset(0f, verticalOffset),
            end = Offset(size.width, verticalOffset)
        )
    }
}
fun Modifier.bringIntoView(
    scrollState: ScrollState
): Modifier = composed {
    var scrollToPosition by remember {
        mutableStateOf(0f)
    }
    val coroutineScope = rememberCoroutineScope()
    this
        .onGloballyPositioned { coordinates ->
            scrollToPosition = scrollState.value + coordinates.positionInRoot().y
        }
        .onFocusEvent {
            if (it.isFocused) {
                coroutineScope.launch {
                    scrollState.animateScrollTo(scrollToPosition.toInt())
                }
            }
        }
}