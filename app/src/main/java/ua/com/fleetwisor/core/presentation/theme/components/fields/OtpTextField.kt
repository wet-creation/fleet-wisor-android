package ua.com.agroswit.theme.components.fields

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.platform.LocalTextToolbar
import androidx.compose.ui.platform.TextToolbar
import androidx.compose.ui.platform.TextToolbarStatus
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme

@Composable
fun OtpTextField(
    value: String,
    onValueChange: (String) -> Unit,
    error: Boolean = false
) {
    CompositionLocalProvider(
        LocalTextToolbar provides EmptyTextToolbar
    ) {
        BasicTextField(
            value = value,
            onValueChange = {
                if (it.trim().length <= 5) {
                    onValueChange(it.trim())
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword
            ),
            decorationBox = {
                Column {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .height(60.dp)
                            .fillMaxWidth()
                    ) {
                        repeat(5) { index ->
                            val char = when {
                                index >= value.length -> ""
                                else -> value[index].toString()
                            }
                            Box(
                                modifier = if (error) {
                                    Modifier
                                        .width(54.4.dp)
                                        .fillMaxHeight()
                                        .border(
                                            1.dp,
                                            FleetWisorTheme.colors.errorNormal,
                                            RoundedCornerShape(10.dp)
                                        )
                                } else if (index == value.length) {
                                    Modifier
                                        .width(54.4.dp)
                                        .fillMaxHeight()
                                        .border(
                                            1.dp,
                                            FleetWisorTheme.colors.brandPrimaryNormal,
                                            RoundedCornerShape(10.dp)
                                        )
                                } else {
                                    Modifier
                                        .width(54.4.dp)
                                        .fillMaxHeight()
                                        .border(
                                            1.dp,
                                            FleetWisorTheme.colors.neutralSecondaryLight,
                                            RoundedCornerShape(10.dp)
                                        )
                                },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = char,
                                    textAlign = TextAlign.Center,
                                    style = FleetWisorTheme.typography.headlineLarge,
                                    color = FleetWisorTheme.colors.neutralSecondaryDark
                                )
                            }
                        }
                    }
                    if (error) {
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(id = R.string.wrong_code),
                            textAlign = TextAlign.Center,
                            style = FleetWisorTheme.typography.labelLargeR,
                            color = FleetWisorTheme.colors.errorDark
                        )
                    }
                }
            }
        )
    }
}

object EmptyTextToolbar: TextToolbar {
    override val status: TextToolbarStatus = TextToolbarStatus.Hidden

    override fun hide() {  }

    override fun showMenu(
        rect: Rect,
        onCopyRequested: (() -> Unit)?,
        onPasteRequested: (() -> Unit)?,
        onCutRequested: (() -> Unit)?,
        onSelectAllRequested: (() -> Unit)?,
    ) {
    }
}

@Preview
@Composable
fun OtpTextFieldPreview() {
    FleetWisorTheme {
        OtpTextField(value = "45", onValueChange = {}, error = true)
    }
}