package ua.com.fleetwisor.core.presentation.ui.utils

import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import ua.com.fleetwisor.core.domain.utils.Config
import ua.com.fleetwisor.core.domain.utils.ImageUrl

val buildConfig = Config

internal fun ImageUrl.toImageUrl(): String =
    if (this.contains("/")) (buildConfig.baseUrl + "/image" + this) else ("${buildConfig.baseUrl}/image/$this")

@Composable
@NonRestartableComposable
fun rememberAsyncImagePainter(
    model: ImageUrl?,
    @DrawableRes placeholder: Int = 0,
    isSvg: Boolean = true
): Painter {
    return if (isSvg) coil.compose.rememberAsyncImagePainter(
        model = ImageRequest.Builder(
            LocalContext.current
        )
            .data(
                model?.toImageUrl()
            )
            .placeholder(placeholder)
            .error(placeholder)
            .decoderFactory(SvgDecoder.Factory())
            .build()
    ) else coil.compose.rememberAsyncImagePainter(
        model = ImageRequest.Builder(
            LocalContext.current
        )
            .data(
                model?.toImageUrl()
            )
            .build()
    )
}

@Composable
@NonRestartableComposable
fun rememberAsyncImagePainter(
    model: Uri?,
    @DrawableRes placeholder: Int = 0,
    isSvg: Boolean = false
): Painter {
    return if (isSvg) coil.compose.rememberAsyncImagePainter(
        model = ImageRequest.Builder(
            LocalContext.current
        )
            .data(
                model
            )
            .placeholder(placeholder)
            .error(placeholder)
            .decoderFactory(SvgDecoder.Factory())
            .build()
    ) else coil.compose.rememberAsyncImagePainter(
        model = ImageRequest.Builder(
            LocalContext.current
        )
            .data(
                model
            )
            .build()
    )
}


