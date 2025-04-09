package ua.com.agroswit.theme.components.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.domain.utils.ImageUrl
import ua.com.fleetwisor.core.domain.utils.toPriceString
import ua.com.fleetwisor.core.domain.utils.toVolumeString
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.theme.components.select_controls.AmountControl
import ua.com.fleetwisor.core.presentation.ui.utils.rememberAsyncImagePainter
import java.math.BigDecimal

data class OrderProductItemState(
    val name: String,
    val imageUrl: ImageUrl?,
    val packageName: String,
    val amount: Int,
    val volume: Double,
    val volumeUnit: String,
    val sum: BigDecimal
)

@Composable
fun OrderProductItem(
    modifier: Modifier = Modifier,
    state: OrderProductItemState,
    isEditable: Boolean = false,
    amount: Int = 1,
    onAmountChange: (Int) -> Unit = {},
    onRemove: () -> Unit = { }
) {
    if (isEditable)
        OrderProductItemEditable(
            state = state,
            onRemove = onRemove,
            amount = amount,
            onAmountChange = onAmountChange,
            modifier = modifier
        )
    else OrderProductItemDefault(
        state = state,
        modifier = modifier
    )
}


@Composable
private fun OrderProductItemEditable(
    modifier: Modifier = Modifier,
    state: OrderProductItemState,
    amount: Int = 1,
    onAmountChange: (Int) -> Unit = {},
    onRemove: () -> Unit = { }
) {
    Row(
        modifier = modifier.height(IntrinsicSize.Max),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(60.dp),
            painter = rememberAsyncImagePainter(
                model = state.imageUrl,
                placeholder = R.drawable.product_placeholder
            ), contentDescription = ""
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
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
                    tint = FleetWisorTheme.colors.neutralSecondaryNormal,
                    painter = FleetWisorTheme.icons.cross,
                    contentDescription = "",
                    modifier = Modifier
                        .size(12.dp)
                        .clickable {
                            onRemove()
                        }
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Row {
                        Text(
                            text = "${state.packageName}: ",
                            color = FleetWisorTheme.colors.neutralSecondaryNormal,
                            style = FleetWisorTheme.typography.bodyMedium
                        )
                        Text(
                            text = "${state.volume.toVolumeString()} ${state.volumeUnit}",
                            color = FleetWisorTheme.colors.neutralSecondaryNormal,
                            style = FleetWisorTheme.typography.bodyMedium
                        )
                    }

                    AmountControl(amount = amount, onAmountChange = onAmountChange)
                }
                Text(
                    text = "${state.sum.toPriceString()} грн",
                    color = FleetWisorTheme.colors.brandPrimaryNormal,
                    style = FleetWisorTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
private fun OrderProductItemDefault(
    modifier: Modifier = Modifier,
    state: OrderProductItemState,
) {
    Row(
        modifier = modifier.height(IntrinsicSize.Max),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(60.dp),
            painter = rememberAsyncImagePainter(
                model = state.imageUrl,
                placeholder = R.drawable.product_placeholder
            ), contentDescription = ""
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = state.name,
                    style = FleetWisorTheme.typography.titleMedium,
                    color = FleetWisorTheme.colors.brandPrimaryNormal
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Row {
                            Text(
                                text = "${state.packageName}: ",
                                color = FleetWisorTheme.colors.neutralSecondaryNormal,
                                style = FleetWisorTheme.typography.bodyMedium
                            )
                            Text(
                                text = "${state.volume.toVolumeString()} ${state.volumeUnit}",
                                color = FleetWisorTheme.colors.neutralSecondaryNormal,
                                style = FleetWisorTheme.typography.bodyMedium
                            )
                        }
                        Row {
                            Text(
                                text = stringResource(id = R.string.amount_detail_text),
                                color = FleetWisorTheme.colors.neutralSecondaryDark,
                                style = FleetWisorTheme.typography.titleSmall
                            )
                            Text(
                                text = " ${state.amount}",
                                color = FleetWisorTheme.colors.neutralSecondaryDark,
                                style = FleetWisorTheme.typography.titleSmall
                            )
                        }
                    }

                }
            }
            Text(
                text = "${state.sum.toPriceString()} грн",
                color = FleetWisorTheme.colors.brandPrimaryNormal,
                style = FleetWisorTheme.typography.bodyMedium
            )
        }

    }
}

@Preview
@Composable
private fun Preview() {
    OrderProductItem(
        isEditable = false,
        state = OrderProductItemState(
            name = "Раундап® Макс ",
            imageUrl = null,
            packageName = "Каністра",
            amount = 2,
            volume = 5.0,
            volumeUnit = "л",
            sum = BigDecimal(2552.0)
        ), amount = 1
    )
}