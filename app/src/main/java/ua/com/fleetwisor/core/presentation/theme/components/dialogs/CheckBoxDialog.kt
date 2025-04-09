package ua.com.fleetwisor.core.presentation.theme.components.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.agroswit.theme.components.buttons.standart.PrimaryButton
import ua.com.agroswit.theme.components.dialogs.AgroswitDialog
import ua.com.agroswit.theme.components.select_controls.TextCheckbox
import ua.com.agroswit.theme.components.select_controls.TextCheckboxState
import ua.com.fleetwisor.R

@Composable
fun CheckBoxDialog(
    modifier: Modifier = Modifier,
    text: String,
    onDismiss: () -> Unit,
    onClick: () -> Unit
) {
    var state by remember {
        mutableStateOf(TextCheckboxState(text = text))
    }

    AgroswitDialog(
        modifier = modifier,
        onDismiss = onDismiss
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 20.dp,
                    horizontal = 12.dp
                ),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextCheckbox(
                textCheckboxState = state
            ) {
                state = state.copy(checked = !state.checked)
            }

            PrimaryButton(
                text = stringResource(R.string.continue_text),
                contentPadding = PaddingValues(horizontal = 32.dp),
                enabled = state.checked
            ) {
                onClick()
            }
        }
    }
}

@Preview
@Composable
private fun CheckBoxDialogPrev() {
    CheckBoxDialog(
        text = "BABdfkjkjdfkjdfkjdfkjdfjfjkfjfdjkdfjkdfjkfdjkfdjdfjfdjkdfjdkjdfjfddkjjfkdjkdfABABAB",
        onDismiss = { }
    ) {

    }
}