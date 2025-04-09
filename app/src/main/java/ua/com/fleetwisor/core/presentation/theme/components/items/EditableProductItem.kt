package ua.com.agroswit.theme.components.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.core.domain.utils.ImageUrl
import ua.com.fleetwisor.core.domain.utils.Index
import ua.com.fleetwisor.core.domain.utils.toPriceString
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.agroswit.theme.components.dropdown.SelectedDropDown
import ua.com.fleetwisor.core.presentation.theme.components.select_controls.AmountControl
import ua.com.agroswit.theme.components.select_controls.DropDownItemState
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.presentation.ui.utils.rememberAsyncImagePainter
import java.math.BigDecimal

data class EditableProductItemState(
    val name: String,
    val imageUrl: ImageUrl?,
    val packageName: String,
    val volumes: List<DropDownItemState>,
)


@Composable
fun EditableProductItem(
    modifier: Modifier = Modifier,
    state: EditableProductItemState,
    amount: Int,
    selectedVolumeIndex: Index,
    sum: BigDecimal,
    inStock: Boolean = true,
    categoryName: String? = null,
    onVolumeChange: (Int) -> Unit,
    onAmountChange: (Int) -> Unit,
    onItemRemove: () -> Unit
) {

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.height(65.dp)
        ) {
            if(!inStock) {
                Box(modifier = Modifier.wrapContentSize()) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = state.imageUrl,
                            placeholder = R.drawable.product_placeholder
                        ),
                        contentDescription = "",
                        modifier = Modifier.size(65.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.out_of_stock_text),
                        style = FleetWisorTheme.typography.labelSmallSB,
                        textAlign = TextAlign.Center,
                        color = FleetWisorTheme.colors.brandPrimaryNormal,
                        modifier = Modifier
                            .background(FleetWisorTheme.colors.neutralPrimaryLight)
                            .width(65.dp)
                            .padding(2.dp)
                            .align(Alignment.Center)
                    )
                }
            } else {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = state.imageUrl,
                        placeholder = R.drawable.product_placeholder
                    ),
                    contentDescription = "",
                    modifier = Modifier.size(65.dp)
                )
            }
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = state.name,
                        softWrap = false,
                        overflow = TextOverflow.Ellipsis,
                        style = FleetWisorTheme.typography.titleMedium,
                        color = FleetWisorTheme.colors.brandPrimaryNormal
                    )
                    Icon(
                        modifier = Modifier
                            .size(16.dp)
                            .clickable {
                                onItemRemove()
                            },
                        painter = FleetWisorTheme.icons.cross,
                        tint = FleetWisorTheme.colors.neutralSecondaryNormal,
                        contentDescription = ""
                    )
                }
                if (!categoryName.isNullOrBlank()) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${stringResource(id = R.string.category)}: ",
                            style = FleetWisorTheme.typography.bodyMedium,
                            color = FleetWisorTheme.colors.neutralSecondaryDark
                        )
                        Text(
                            text = categoryName,
                            style = FleetWisorTheme.typography.titleSmall,
                            color = FleetWisorTheme.colors.neutralSecondaryDark
                        )
                    }

                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${state.packageName}: ",
                        style = FleetWisorTheme.typography.bodyMedium,
                        color = FleetWisorTheme.colors.neutralSecondaryDark
                    )
                    if (state.volumes.size > 1)
                        SelectedDropDown(
                            selectedItemIndex = selectedVolumeIndex,
                            items = {
                                state.volumes
                            },
                            onItemChange = onVolumeChange,
                            modifier = Modifier
                                .width(90.dp)
                        )
                    else Text(
                        text = state.volumes[0].text,
                        style = FleetWisorTheme.typography.bodyMedium,
                        color = FleetWisorTheme.colors.neutralSecondaryDark
                    )


                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AmountControl(amount = amount, onAmountChange = onAmountChange)
            Text(
                text = "${sum.toPriceString()} грн",
                color = FleetWisorTheme.colors.brandPrimaryNormal,
                style = FleetWisorTheme.typography.titleSmall
            )
        }
    }
}

@Preview
@Composable
private fun Previewwww() {
    var value by remember {
        mutableIntStateOf(1)
    }
    var selectedVolume by remember {
        mutableIntStateOf(1)
    }
    FleetWisorTheme {
        EditableProductItem(
            state = EditableProductItemState(
                name = "Daena", imageUrl = null, volumes = listOf(
                    DropDownItemState(0, "1л"),
                    DropDownItemState(1, "2л"),
                ),
                packageName = "Каністра"

            ),
            amount = value,
            selectedVolumeIndex = selectedVolume,
            onVolumeChange = {
                selectedVolume = it
            },
            onAmountChange = {
                value = it
            },
            sum = BigDecimal(72.377),
            categoryName = "Гербіциди",
            inStock = false
        ) {

        }

    }
}