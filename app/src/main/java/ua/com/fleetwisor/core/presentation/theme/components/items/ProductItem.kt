package ua.com.fleetwisor.core.presentation.theme.components.items

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.agroswit.theme.components.items.ProductItemLoading
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.theme.components.badges.AgroswitCircleBadge
import ua.com.agroswit.theme.components.navigation.AgroswitChip
import ua.com.fleetwisor.R

data class ProductItemState(
    val isNew: Boolean,
    val producer: String,
    val isRecommended: Boolean,
    val packageVolume: String,
    val image: Painter,
    val price: String,
    val oldPrice: String?,
    val category: String,
    val name: String,
    val packageType: String,
    val activeIngredients: String,
    val shortDescription: String,
    val inStock: Boolean,
    val isLoading: Boolean = false
)

@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    state: ProductItemState,
    onClick: () -> Unit
) {
    if (!state.isLoading)
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(144.dp),
            colors = CardDefaults.cardColors().copy(
                containerColor = FleetWisorTheme.colors.neutralPrimaryLight,

                ),
            border = BorderStroke(1.dp, FleetWisorTheme.colors.neutralSecondaryLight),
            onClick = onClick
        ) {
            Box {
                if (state.isRecommended)
                    AgroswitCircleBadge(
                        contentPadding = PaddingValues(6.dp),
                        icon = FleetWisorTheme.icons.promotionalOffersFilled,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 12.dp, end = 12.dp)
                    )
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
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(94.dp)
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
                                contentDescription = "",
                                modifier = Modifier
                                    .size(94.dp)
                                    .align(Alignment.CenterHorizontally)
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
                                text = stringResource(id = R.string.producer) + ": ",
                                style = FleetWisorTheme.typography.titleSmall,
                                color = FleetWisorTheme.colors.neutralSecondaryDark
                            )
                            Text(
                                text = state.producer,
                                style = FleetWisorTheme.typography.bodyMedium,
                                color = FleetWisorTheme.colors.neutralSecondaryDark
                            )
                        }
                        Row {
                            Text(
                                text = state.packageType + ": ",
                                style = FleetWisorTheme.typography.titleSmall,
                                color = FleetWisorTheme.colors.neutralSecondaryDark
                            )
                            Text(
                                text = state.packageVolume,
                                style = FleetWisorTheme.typography.bodyMedium,
                                color = FleetWisorTheme.colors.neutralSecondaryDark
                            )
                        }
                        if (state.activeIngredients.isNotEmpty()) {
                            Row {
                                Text(
                                    text = stringResource(id = R.string.active_ingridient_short_text) + ": ",
                                    style = FleetWisorTheme.typography.titleSmall,
                                    color = FleetWisorTheme.colors.neutralSecondaryDark,
                                )
                                Text(
                                    text = state.activeIngredients,
                                    style = FleetWisorTheme.typography.bodyMedium,
                                    color = FleetWisorTheme.colors.neutralSecondaryDark,
                                    softWrap = false,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        } else {
                            Row {
                                Text(
                                    text = state.shortDescription,
                                    style = FleetWisorTheme.typography.titleSmall,
                                    color = FleetWisorTheme.colors.neutralSecondaryDark
                                )
                            }
                        }
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                verticalAlignment = Alignment.Bottom,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = state.price + " " + stringResource(id = R.string.currency_uah_text),
                                    style = FleetWisorTheme.typography.titleMedium,
                                    color = FleetWisorTheme.colors.brandPrimaryNormal
                                )
                                if (state.isRecommended)
                                    state.oldPrice?.let {
                                        if (it != "0")
                                            Text(
                                                text = it + " " + stringResource(id = R.string.currency_uah_text),
                                                style = FleetWisorTheme.typography.labelMediumSB.copy(
                                                    textDecoration = TextDecoration.LineThrough
                                                ),
                                                color = FleetWisorTheme.colors.neutralSecondaryNormal,

                                                )
                                    }
                            }
                            AgroswitChip(
                                textStyle = FleetWisorTheme.typography.labelMediumR,
                                modifier = Modifier
                                    .height(16.dp)
                                    .padding(horizontal = 8.dp, vertical = 1.dp),
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


@Preview(
    device = "spec:width=720px,height=1650px", apiLevel = 34,
    showSystemUi = true, showBackground = true
)
@Composable
private fun ProductItemPrev() {
    Column(
        Modifier
            .background(Color.White)
    ) {
        ProductItem(
            modifier = Modifier
                .fillMaxWidth()
                .height(144.dp),
            state = ProductItemState(
                name = "Раундап® Макс",
                producer = "Bayer",
                packageVolume = "20 л",
                activeIngredients = "гліфосату 450 г/л у кислотному еквіваленті",
                price = "6 966.00",
                isNew = false,
                isRecommended = true,
                image = painterResource(R.drawable.product_placeholder),
                category = "Гербіциди",
                inStock = true,
                oldPrice = null,
                packageType = "Каністра",
                shortDescription = "Раундап® Макс — неселективний гербіцид суцільної дії  "
            )
        ) {}

        ProductItem(
            modifier = Modifier
                .fillMaxWidth()
                .height(144.dp),
            state = ProductItemState(
                name = "Раундап® Макс",
                isRecommended = true,
                producer = "Bayer",
                packageVolume = "20 л",
                activeIngredients = "",
                price = "6 966.00",
                isNew = true,
                packageType = "Каністра",
                inStock = false,
                oldPrice = "2332",
                image = painterResource(R.drawable.product_placeholder),
                category = "Гербіциди",
                shortDescription = "Раундап® Макс — неселективний гербіцид суцільної дії  "
            )
        ) {}
    }
}