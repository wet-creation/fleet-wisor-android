package ua.com.fleetwisor.core.presentation.theme.components.images

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme

@Composable
fun BorderImage(modifier: Modifier = Modifier, image: Painter, contentDescription: String) {
    Box(
        modifier.border(
            1.dp,
            color = FleetWisorTheme.colors.brandPrimaryMedium,
            shape = RoundedCornerShape(5.dp)
        )
    ) {
        Image(
            contentScale = ContentScale.Crop,
            painter = image,
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(5.dp)).align(Alignment.Center)
        )
    }
}

@Preview
@Composable
private fun BorderImagePrev() {
    BorderImage(
        image = painterResource(R.drawable.producer_placeholder),
        contentDescription =""
    )
}