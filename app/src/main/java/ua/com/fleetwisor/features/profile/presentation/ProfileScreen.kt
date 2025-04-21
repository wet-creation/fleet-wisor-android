package ua.com.fleetwisor.features.profile.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ua.com.agroswit.theme.components.buttons.standart.PrimaryButton
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.theme.components.buttons.standart.SecondaryNormalButton
import ua.com.fleetwisor.core.presentation.theme.components.fields.LabelTextButton
import ua.com.fleetwisor.core.presentation.theme.components.fields.SimplePasswordTextFieldAgroswit
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.FleetWisorScaffold
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.SimpleFilledAgroswitTopAppBar

@Composable
fun ProfileRoot(
    viewModel: ProfileViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ProfileScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun ProfileScreen(
    state: ProfileState,
    onAction: (ProfileAction) -> Unit,
) {
    FleetWisorScaffold(
        topAppBar = {
            SimpleFilledAgroswitTopAppBar(
                title = stringResource(R.string.profile_text)
            )
        },
        hasBottomBar = true,

        ) { paddingValue ->
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValue.calculateTopPadding() + 20.dp,
                    bottom = paddingValue.calculateBottomPadding()
                )
                .padding(horizontal = 20.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    stringResource(R.string.personal_data_text),
                    color = FleetWisorTheme.colors.brandPrimaryMedium,
                    style = FleetWisorTheme.typography.headlineMedium
                )
                Column(modifier = Modifier.fillMaxWidth(0.9f)) {
                    LabelTextButton(
                        icon = FleetWisorTheme.icons.person,
                        text = "Прізвище",
                        hasLine = true
                    ) {

                    }
                    LabelTextButton(
                        icon = FleetWisorTheme.icons.fop,
                        text = "Імʼя",
                        hasLine = true
                    ) {

                    }
                    LabelTextButton(
                        icon = FleetWisorTheme.icons.email,
                        text = "email@mail.com",
                        hasLine = true
                    ) {

                    }

                }
                HorizontalDivider()
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    stringResource(R.string.change_password_text),
                    color = FleetWisorTheme.colors.brandPrimaryMedium,
                    style = FleetWisorTheme.typography.headlineMedium
                )
                Column {
                    SimplePasswordTextFieldAgroswit(
                        isPasswordVisible = false,
                        hint = stringResource(R.string.old_password),
                        onValueChange = {}
                    ) { }
                    SimplePasswordTextFieldAgroswit(
                        isPasswordVisible = false,
                        hint = stringResource(R.string.new_password),
                        onValueChange = {}
                    ) { }
                    SimplePasswordTextFieldAgroswit(
                        isPasswordVisible = false,
                        hint = stringResource(R.string.password_check_text),
                        onValueChange = {}
                    ) { }

                }
                PrimaryButton(
                    enabled = false,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = stringResource(R.string.apply_text),
                    contentPadding = PaddingValues(vertical = 12.dp, horizontal = 32.dp)
                ) { }
                HorizontalDivider()
            }

            SecondaryNormalButton(
                modifier = Modifier
                    .padding(vertical = 12.dp, horizontal = 32.dp)
                    .align(Alignment.CenterHorizontally),
                text = stringResource(R.string.logout_text),
            ) {
                onAction(ProfileAction.OnLogOut)
            }

        }

    }
}

@Preview
@Composable
private fun Preview() {
    FleetWisorTheme {
        ProfileScreen(
            state = ProfileState(),
            onAction = {}
        )
    }
}