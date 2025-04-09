package ua.com.fleetwisor.core.presentation.theme.components.items

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.agroswit.theme.components.items.ProductItemLoading
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.agroswit.theme.components.navigation.AgroswitChip
import ua.com.fleetwisor.R

data class SimpleProductItemState(
    val isNew: Boolean,
    val image: Painter,
    val price: String,
    val category: String,
    val name: String,
    val shortDescription: String,
    val inStock: Boolean,
    val isLoading: Boolean = false
)

@Composable
fun SimpleProductItem(
    modifier: Modifier = Modifier,
    state: SimpleProductItemState,
    onClick: () -> Unit
) {
    if (!state.isLoading)
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(144.dp),
            colors = CardDefaults.cardColors().copy(
                containerColor = FleetWisorTheme.colors.neutralPrimaryLight
            ),
            border = BorderStroke(1.dp, FleetWisorTheme.colors.neutralSecondaryLight),
            onClick = onClick
        ) {
            Box {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .padding(end = 12.dp, top = 12.dp, bottom = 12.dp, start = 12.dp)
                        .fillMaxWidth()

                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .weight(0.3f)
                            .fillMaxHeight()
                    ) {
                        if (state.isNew) {
                            Row {
                                AgroswitChip(
                                    modifier = Modifier
                                        .padding(horizontal = 8.dp)
                                        .height(13.dp),
                                    text = stringResource(id = R.string.new_product_text),
                                )
                            }
                        }
                        if (!state.inStock)
                            Box(modifier = Modifier.wrapContentSize()) {
                                Image(
                                    painter = state.image,
                                    contentScale = ContentScale.Crop,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .align(Alignment.Center)
                                )
                                Text(
                                    text = stringResource(id = R.string.out_of_stock_text),
                                    style = FleetWisorTheme.typography.labelSmallSB,
                                    textAlign = TextAlign.Center,
                                    color = FleetWisorTheme.colors.brandPrimaryNormal,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(FleetWisorTheme.colors.neutralPrimaryLight)
                                        .padding(2.dp)
                                        .align(Alignment.Center)
                                )
                            }
                        else {
                            Image(
                                painter = state.image,
                                contentScale = ContentScale.Crop,
                                contentDescription = "",
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                        }
                    }
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .weight(0.7f)
                            .fillMaxHeight()
                    ) {
                        Text(
                            text = state.name,
                            style = FleetWisorTheme.typography.titleLarge,
                            color = FleetWisorTheme.colors.brandPrimaryNormal,
                            softWrap = false,
                            overflow = TextOverflow.Ellipsis
                        )
                            Row {
                                Text(
                                    text = state.shortDescription,
                                    style = FleetWisorTheme.typography.titleSmall,
                                    color = FleetWisorTheme.colors.neutralSecondaryDark
                                )
                            }
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = state.price + " " + stringResource(id = R.string.currency_uah_text),
                                style = FleetWisorTheme.typography.titleMedium,
                                color = FleetWisorTheme.colors.brandPrimaryNormal
                            )
                            AgroswitChip(
                                textStyle = FleetWisorTheme.typography.labelMediumR,
                                modifier = Modifier
                                    .height(16.dp)
                                    .padding(horizontal = 8.dp, vertical = 2.dp),
                                textColor = FleetWisorTheme.colors.brandPrimaryNormal,
                                backgroundColor = FleetWisorTheme.colors.brandPrimaryExtraLight,
                                text = state.category,
                            )
                        }
                    }
                }

            }
        }
    else
        ProductItemLoading(modifier)
}


@Preview
@Composable
private fun ProductItemPrev() {
    Column(
        Modifier
            .background(Color.White)
    ) {
        SimpleProductItem(
            modifier = Modifier
                .width(320.dp)
                .height(144.dp),
            state = SimpleProductItemState(
                name = "Раундап® Макс",
                price = "6 966.00",
                isNew = false,
                image = FleetWisorTheme.icons.catalogFilled,
                category = "Гербіциди",
                inStock = true,
                shortDescription = "Раундап® Макс — неселективний гербіцид суцільної дії  "
            )
        ) {}

        SimpleProductItem(
            modifier = Modifier
                .width(320.dp)
                .height(144.dp),
            state = SimpleProductItemState(
                name = "Раундап® Макс",
                price = "6 966.00",
                isNew = true,
                image = FleetWisorTheme.icons.catalogFilled,
                category = "Гербіциди",
                inStock = true,
                shortDescription = "Раундап® Макс — неселективний гербіцид суцільної дії  "
            )
        ) {}
    }
}