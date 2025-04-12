package ua.com.fleetwisor.features.cars.presentation.cars.common.compoents

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ua.com.fleetwisor.features.cars.domain.models.Insurance
import ua.com.fleetwisor.features.cars.presentation.cars.create.CarCreateAction
import ua.com.fleetwisor.features.cars.presentation.cars.edit.CarEditAction

@Composable
inline fun <reified Action > InsuranceTab(
    insurance: Insurance,
    crossinline onAction: (Action) -> Unit
) {
    val editMode =
        if (CarCreateAction is Action)
            false
        else if (CarEditAction is Action)
            true
        else return
}

@Preview
@Composable
private fun InsuranceTabPrev() {
    InsuranceTab(
        insurance = Insurance()
    ) { it: CarCreateAction ->

    }
}