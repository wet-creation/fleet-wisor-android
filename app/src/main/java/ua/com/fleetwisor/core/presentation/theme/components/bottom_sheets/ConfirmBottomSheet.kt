package ua.com.fleetwisor.core.presentation.theme.components.bottom_sheets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.agroswit.theme.components.buttons.standart.PrimaryButton
import ua.com.fleetwisor.core.presentation.theme.components.buttons.standart.SecondaryNormalButton

@Composable
fun ConfirmBottomSheet(
    title: String,
    upButtonText: String,
    bottomButtonText: String,
    onUpButtonClick: () -> Unit,
    onBottomButtonClick: () -> Unit
){
    Column(
        modifier = Modifier.padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = FleetWisorTheme.typography.titleLarge,
            color = FleetWisorTheme.colors.neutralSecondaryDark
        )
        Spacer(modifier = Modifier.height(20.dp))
        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = upButtonText,
            onClick = onUpButtonClick
        )
        Spacer(modifier = Modifier.height(12.dp))
        SecondaryNormalButton(
            modifier = Modifier.fillMaxWidth(),
            text = bottomButtonText,
            onClick = onBottomButtonClick
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}