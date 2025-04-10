package ua.com.agroswit.theme.components.fields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.theme.components.fields.SimpleTextFieldAgroswit

@Composable
fun ParamInput(
    modifier: Modifier = Modifier,
    name: String,
    unit: String,
    enabled: Boolean = true,
    titleStyle: TextStyle = FleetWisorTheme.typography.titleMedium,
    value: String,
    onChange: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = name,
            color = FleetWisorTheme.colors.brandPrimaryNormal,
            style = titleStyle
        )
        ParamInputTextField(
            modifier = modifier,
            value = value,
            onChange = onChange,
            unit = unit,
            enabled = enabled
        )

    }
}


@Composable
fun ParamInputTextField(
    modifier: Modifier = Modifier, value: String,
    unit: String,
    enabled: Boolean = true,
    onChange: (String) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {

        SimpleTextFieldAgroswit(
            enabled = enabled,
            value = value,
            keyboardType = KeyboardType.Number,
            hint = "1",
            textAlign = TextAlign.End,
            modifier = modifier
                .border(
                    width = 1.dp,
                    color = FleetWisorTheme.colors.brandPrimaryLight,
                    shape = RoundedCornerShape(size = 4.dp)
                )
                .width(76.dp)
                .height(27.dp)
                .background(
                    color = FleetWisorTheme.colors.neutralPrimaryLight,
                    shape = RoundedCornerShape(size = 4.dp)
                )

                .padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp),
            onValueChange = onChange
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = unit,
            style = FleetWisorTheme.typography.titleLarge,
            color = FleetWisorTheme.colors.neutralSecondaryDark
        )
    }
}

@Preview
@Composable
private fun SchemeParamInputPrev() {
    ParamInput(value = "", name = "Загальна площа поля ", unit = "га") {

    }
}