package ua.com.fleetwisor.features.cars.presentation.cars.common.compoents

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.core.presentation.theme.components.buttons.standart.SecondaryLongIconButton
import ua.com.agroswit.theme.components.dropdown.SelectedDropDownElement
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.ui.utils.rememberAsyncImagePainter
import ua.com.fleetwisor.features.cars.domain.models.Insurance
import ua.com.fleetwisor.features.cars.presentation.cars.create.CarCreateAction
import ua.com.fleetwisor.features.cars.presentation.cars.edit.CarEditAction

@Composable
inline fun <reified Action> InsuranceTab(
    insurance: Insurance,
    crossinline onAction: (Action) -> Unit
) {
    val editMode =
        if (CarCreateAction is Action)
            false
        else if (CarEditAction is Action)
            true
        else return
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 20.dp, horizontal = 20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(28.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    stringResource(R.string.insurance_period_text) + ":",
                    color = FleetWisorTheme.colors.brandPrimaryLight,
                    style = FleetWisorTheme.typography.titleMedium
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                        Text("Від:", style = FleetWisorTheme.typography.bodyLarge)
                        Text("До:", style = FleetWisorTheme.typography.bodyLarge)
                    }
                    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.clickable {

                            }
                        ) {
                            SelectedDropDownElement(
                                modifier = Modifier.weight(1f),
                                textItem = "12",
                                active = false,
                            ) {}
                            SelectedDropDownElement(
                                modifier = Modifier.weight(1f),
                                active = false,
                                textItem = "лютий"
                            ) {}
                            SelectedDropDownElement(
                                modifier = Modifier.weight(1f),
                                active = false,
                                textItem = "2025"
                            ) {}
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.clickable {
                            }
                        ) {
                            SelectedDropDownElement(
                                modifier = Modifier.weight(1f),
                                active = false,
                                textItem = "12"
                            ) {}
                            SelectedDropDownElement(
                                modifier = Modifier.weight(1f),
                                active = false,
                                textItem = "березень"
                            ) {}
                            SelectedDropDownElement(
                                modifier = Modifier.weight(1f),
                                active = false,
                                textItem = "2025"
                            ) {}
                        }
                    }
                }
            }
            if (insurance.photoUrl == "")
                SecondaryLongIconButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.add_document),
                    icon = FleetWisorTheme.icons.logout,
                    contentDescription = ""
                ) { }
            else {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        stringResource(R.string.document_text) + ":",
                        color = FleetWisorTheme.colors.brandPrimaryLight,
                        style = FleetWisorTheme.typography.titleMedium
                    )
                    Image(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = FleetWisorTheme.colors.neutralSecondaryDark,
                                shape = RoundedCornerShape(size = 5.dp)
                            ),
                        painter = rememberAsyncImagePainter(
                            insurance.photoUrl,
                            //todo replace placeholder
                            placeholder = R.drawable.product_placeholder
                        ), contentDescription = ""
                    )
                }
            }


        }

    }
}

@Preview
@Composable
private fun InsuranceTabPrev() {
    InsuranceTab(
        insurance = Insurance(
            photoUrl = "s"
        )
    ) { it: CarCreateAction ->

    }
}