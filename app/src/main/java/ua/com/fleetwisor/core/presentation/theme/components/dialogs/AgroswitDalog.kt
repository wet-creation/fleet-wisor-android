package ua.com.fleetwisor.core.presentation.theme.components.dialogs

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme

@Composable
fun AgroswitDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    content: @Composable () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .border(
                    1.dp,
                    color = FleetWisorTheme.colors.brandPrimaryNormal,
                    shape = RoundedCornerShape(8.dp)
                ),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors().copy(
                containerColor = FleetWisorTheme.colors.neutralPrimaryLight,
                contentColor = FleetWisorTheme.colors.neutralSecondaryDark
            )
        ) {
            content()
        }
    }
}