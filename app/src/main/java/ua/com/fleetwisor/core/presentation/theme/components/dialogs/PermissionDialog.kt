package ua.com.fleetwisor.core.presentation.theme.components.dialogs


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.agroswit.theme.components.buttons.standart.PrimaryButton
import ua.com.fleetwisor.R

@Composable
fun PermissionDialog(
    permissionTextProvider: PermissionTextProvider,
    isPermanentlyDeclined: Boolean,
    onDismiss: () -> Unit,
    onOkClick: () -> Unit,
    onGoToAppSettingsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(
        onDismissRequest = onDismiss,
    ) {
        Column(
            modifier
                .background(color = FleetWisorTheme.colors.neutralPrimaryNormal)
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.permission_required),
                style = FleetWisorTheme.typography.headlineSmall,
                color = FleetWisorTheme.colors.brandPrimaryNormal
            )
            Text(
                text = permissionTextProvider.getDescription(
                    isPermanentlyDeclined = isPermanentlyDeclined
                )
            )
            HorizontalDivider()

            PrimaryButton(
                contentPadding = PaddingValues(horizontal = 20.dp),
                text = if (isPermanentlyDeclined) {
                    stringResource(R.string.grant_permission)
                } else {
                    "ОК"
                },
            ) {
                if (isPermanentlyDeclined) {
                    onGoToAppSettingsClick()
                } else {
                    onOkClick()
                }
            }
        }
    }
}

@Preview
@Composable
private fun DialogPrev() {
    PermissionDialog(
        permissionTextProvider = NotificationPermissionTextProvider(),
        isPermanentlyDeclined = false,
        onOkClick = { },
        onGoToAppSettingsClick = {},
        onDismiss = {},
    )
}

interface PermissionTextProvider {
    @Composable
    fun getDescription(isPermanentlyDeclined: Boolean): String
}

class NotificationPermissionTextProvider : PermissionTextProvider {
    @Composable
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined) {
            stringResource(R.string.permission_notification_denied)
        } else {
            stringResource(R.string.permission_notification_request)

        }
    }
}

