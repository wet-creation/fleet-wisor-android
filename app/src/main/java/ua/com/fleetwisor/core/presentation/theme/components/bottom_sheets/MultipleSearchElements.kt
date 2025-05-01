package ua.com.fleetwisor.core.presentation.theme.components.bottom_sheets

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.unit.dp
import ua.com.agroswit.theme.components.buttons.standart.PrimaryButton
import ua.com.agroswit.theme.components.fields.TextFieldAgroswit
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.domain.utils.Index
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme

@Composable
fun MultipleSearchElements(
    modifier: Modifier = Modifier,
    selectedItems: () -> List<Index>,
    itemsList: () -> List<String>,
    inputSearchValue: String,
    inputSearch: (String) -> Unit,
    onSave: (List<Index>) -> Unit
) {

    var elements by remember { mutableStateOf(selectedItems()) }

    Column(
        modifier = modifier,
             verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = stringResource(id = R.string.search_text),
            style = FleetWisorTheme.typography.headlineSmall,
            color = FleetWisorTheme.colors.brandPrimaryNormal
        )
        TextFieldAgroswit(
            value = inputSearchValue,
            tint = FleetWisorTheme.colors.brandPrimaryNormal,
            icon = FleetWisorTheme.icons.search,
            hint = stringResource(id = R.string.search_text),
            onValueChange = inputSearch
        )
        LazyColumn(
            Modifier
                .animateContentSize()
                .weight(1f, false)
                .heightIn(max = 336.dp)
        ) {
            itemsIndexed(itemsList()) { index, item ->
                if (index != 0) {
                    HorizontalDivider()
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                        .clickable {
                            val mutable = elements.toMutableList()

                            if (elements.contains(index)) {
                                mutable.remove(index)
                            } else {
                                mutable.add(index)
                            }
                            elements = mutable
                        },

                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = item,
                        color = FleetWisorTheme.colors.neutralSecondaryDark,
                        style = FleetWisorTheme.typography.bodyLarge,
                    )
                    if (elements.contains(index))
                        Icon(
                            painter = FleetWisorTheme.icons.check,
                            contentDescription = "",
                            tint = FleetWisorTheme.colors.brandPrimaryNormal
                        )
                }

            }
        }
        PrimaryButton(
            text = stringResource(id = R.string.confirm),
            modifier = Modifier.fillMaxWidth(),
        ) {
            onSave(elements)
        }
    }


}
