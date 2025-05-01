package ua.com.agroswit.theme.components.items

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme

@Composable
fun ProductItemLoading(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(144.dp),
        colors = CardDefaults.cardColors().copy(
            containerColor = Color.Transparent,
        ),
        border = BorderStroke(1.dp, FleetWisorTheme.colors.neutralSecondaryLight)
    ) {
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
                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .background(
                            FleetWisorTheme.colors.brandPrimaryLight,
                            shape = MaterialTheme.shapes.medium
                        )
                )
            }
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .weight(0.7f)
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Box(
                    modifier = Modifier
                        .width(142.dp)
                        .height(22.dp)
                        .background(
                            color = Color(0xFFD5ECDE),
                            shape = RoundedCornerShape(size = 2.dp)
                        )

                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .background(
                            color = Color(0xFFD5ECDE),
                            shape = RoundedCornerShape(size = 2.dp)
                        )

                )
                Box(
                    modifier = Modifier
                        .height(16.dp)
                        .aspectRatio(4f)
                        .background(
                            color = FleetWisorTheme.colors.brandPrimaryMedium,
                            shape = RoundedCornerShape(size = 2.dp)
                        )

                )

            }
        }
    }
}


@Preview
@Composable
private fun ProductItemPrev() {
    Column(
        Modifier
            .background(Color.White)
    ) {
        ProductItemLoading(
            modifier = Modifier
                .width(320.dp)
                .height(144.dp),
        )
    }
}