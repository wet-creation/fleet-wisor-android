package ua.com.fleetwisor.core.presentation.theme.components.select_controls

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.theme.components.fields.SimpleTextFieldAgroswit
import ua.com.fleetwisor.R
import java.math.BigDecimal

@Composable
fun AmountControl(modifier: Modifier = Modifier, amount: Int, onAmountChange: (Int) -> Unit) {
    var isFirst by remember { mutableStateOf(false) }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.amount_detail_text),
            color = FleetWisorTheme.colors.neutralSecondaryDark,
            style = FleetWisorTheme.typography.titleSmall
        )
        Icon(
            painter = FleetWisorTheme.icons.minus,
            tint = if (amount > 1) FleetWisorTheme.colors.brandPrimaryNormal else FleetWisorTheme.colors.neutralSecondaryLight,
            contentDescription = "",
            modifier = Modifier
                .clickable(
                    onClick = {
                        onAmountChange(amount - 1)
                    },
                    enabled = amount > 1
                )
                .padding(vertical = 5.dp)
                .size(20.dp)
                .padding(horizontal = 1.dp)
        )
        SimpleTextFieldAgroswit(
            value = amount.toString(),
            textAlign = TextAlign.Center,
            keyboardType = KeyboardType.Number,
            hint = "1",
            modifier = Modifier.width(25.dp),
            textStyle = FleetWisorTheme.typography.labelLargeR,
        ) {
            if (!it.isDigitsOnly())
                return@SimpleTextFieldAgroswit

            if (it.contains(","))
                return@SimpleTextFieldAgroswit

            if (it.length > 3)
                return@SimpleTextFieldAgroswit

            if (it.isBlank() || it.isEmpty()) {
                isFirst = true
                onAmountChange(1)
                return@SimpleTextFieldAgroswit
            }

            if (isFirst) {
                isFirst = false
                val value = it.replace("1", "")
                if (value.isBlank() || value == "0")
                    onAmountChange(1)
                else
                    onAmountChange(value.toInt())
                return@SimpleTextFieldAgroswit
            }
            if (BigDecimal(it) > Int.MAX_VALUE.toBigDecimal())
                return@SimpleTextFieldAgroswit


            onAmountChange(it.toInt())
        }
        Icon(
            painter = FleetWisorTheme.icons.plus,
            tint = if (amount < 999) FleetWisorTheme.colors.brandPrimaryNormal else FleetWisorTheme.colors.neutralSecondaryLight,
            contentDescription = "",
            modifier = Modifier
                .clickable(
                    enabled = amount < 999,
                    onClick = {
                        onAmountChange(amount + 1)
                    },
                )
                .padding(vertical = 5.dp)
                .size(18.dp)
                .padding(horizontal = 1.dp)
        )
    }
}

@Preview
@Composable
private fun Prev() {
    AmountControl(amount = 2) {

    }
}