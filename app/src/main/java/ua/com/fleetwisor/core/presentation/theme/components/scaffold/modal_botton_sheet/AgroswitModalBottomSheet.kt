package ua.com.agroswit.theme.components.scaffold.modal_botton_sheet

import android.view.Gravity
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme

@Composable
fun AgroswitModalBottomSheet(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    isVisible: Boolean,
    content: @Composable ColumnScope.() -> Unit
) {
    val keyBoardPadding = WindowInsets.ime.asPaddingValues()
    val animatePadding by animateDpAsState(
        targetValue = keyBoardPadding.calculateBottomPadding(),
        label = "bottom_padding"
    )
    if (isVisible)
        Dialog(
            onDismissRequest = { onDismissRequest() },
            properties = DialogProperties(
                usePlatformDefaultWidth = false,
                dismissOnClickOutside = true
            )
        ) {
            val dialogWindowProvider = LocalView.current.parent as DialogWindowProvider
            dialogWindowProvider.window.setGravity(Gravity.BOTTOM)
            Box(
                modifier = Modifier
                    .padding(bottom = animatePadding)
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                ScrimBackground(onDismissRequest)
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier
                        .clip(
                            RoundedCornerShape(
                                topStart = 20.dp,
                                topEnd = 20.dp
                            )
                        )
                        .pointerInput(Unit) {}
                        .background(FleetWisorTheme.colors.neutralPrimaryLight)
                ) {
                    DragHandle()
                    content()
                }
            }
        }

}

@Composable
private fun DragHandle(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Surface(
            modifier = modifier
                .padding(vertical = 22.dp),
            color = FleetWisorTheme.colors.neutralSecondaryNormal,
            shape = MaterialTheme.shapes.extraLarge,
        ) {
            Box(
                Modifier
                    .size(
                        width = 32.dp,
                        height = 4.dp
                    )
            )
        }
    }

}

@Composable
private fun ScrimBackground(
    onClick: () -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) { detectTapGestures { onClick() } }
            .background(color = Color.Black.copy(alpha = 0.32f)),
    )
}

@Preview
@Composable
private fun AgroswitModalBottomSheetPrev() {
    AgroswitModalBottomSheet(
        isVisible = true,
        onDismissRequest = {}
    ) {

    }
}



