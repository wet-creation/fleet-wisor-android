package ua.com.fleetwisor.features.cars.presentation.fill_up.common.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.theme.components.buttons.standart.SecondaryLongIconButton
import ua.com.fleetwisor.core.presentation.theme.components.images.BorderImage
import ua.com.fleetwisor.core.presentation.ui.utils.rememberAsyncImagePainter
import ua.com.fleetwisor.features.cars.domain.models.FillUp
import ua.com.fleetwisor.features.cars.presentation.fill_up.create.FillUpCreateAction
import ua.com.fleetwisor.features.cars.presentation.fill_up.edit.FillUpEditAction

@Composable
inline fun <reified Action> FillUpCheckTab(
    fillUp: FillUp,
    selectedPhoto: Uri?,
    crossinline onAction: (Action) -> Unit,
) {
    val editMode =
        if (FillUpCreateAction is Action)
            false
        else if (FillUpEditAction is Action)
            true
        else return

    val pickPhoto = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            if (editMode) {
                onAction(FillUpEditAction.SelectPhoto(uri) as Action)

            } else {
                onAction(FillUpCreateAction.SelectPhoto(uri) as Action)
            }

        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 20.dp, horizontal = 20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(28.dp)
    ) {
        if (selectedPhoto != null) {
            BorderImage(
                modifier = Modifier
                    .size(150.dp)
                    .clickable {
                        pickPhoto.launch("image/*")
                    },
                image = rememberAsyncImagePainter(selectedPhoto),
                contentDescription = ""
            )
        } else if (fillUp.checkUrl != null) {
            BorderImage(
                modifier = Modifier
                    .size(150.dp)
                    .clickable {
                        pickPhoto.launch("image/*")
                    },
                image = rememberAsyncImagePainter(fillUp.checkUrl),
                contentDescription = ""
            )
        } else {
            SecondaryLongIconButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.add_check),
                icon = FleetWisorTheme.icons.logout,
                contentDescription = ""
            ) {
                pickPhoto.launch("image/*")

            }

        }
    }
}


@Preview
@Composable
private fun FillUpCheckTabPrev() {
    FillUpCheckTab<FillUpCreateAction>(
        fillUp = FillUp(),
        selectedPhoto = null,
        onAction = {}
    )
}