package ua.com.agroswit.theme


import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import ua.com.fleetwisor.R

val TextUnit.nonScaledSp
    @Composable
    get() = (this.value / LocalDensity.current.fontScale).sp

@Composable
fun applyNonScaledSpToTextStyle(style: TextStyle): TextStyle {
    return style.copy(fontSize = style.fontSize.nonScaledSp)
}

data class AgroswitTypography(
    val headlineLarge: TextStyle
    = TextStyle(
        fontSize = 24.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(600)
    ),
    val headlineMedium: TextStyle
    = TextStyle(
        fontSize = 20.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(600)
    ),
    val headlineSmall: TextStyle
    = TextStyle(
        fontSize = 18.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(700)
    ),
    val titleLarge: TextStyle
    = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(600)
    ),
    val titleSmall: TextStyle
    = TextStyle(
        fontSize = 12.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(700)
    ),
    val titleMedium: TextStyle
    = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(700)
    ),
    val bodyLarge: TextStyle
    = TextStyle(
        fontSize = 14.sp,
        lineHeight = 19.6.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(400)
    ),
    val bodyMedium: TextStyle
    = TextStyle(
        fontSize = 12.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(400)
    ),
    val bodySmall: TextStyle
    = TextStyle(
        fontSize = 10.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(400)
    ),
    val labelLargeM: TextStyle
    = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(500)

    ),
    val labelLargeR: TextStyle
    = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(400)
    ),
    val labelMediumR: TextStyle
    = TextStyle(
        fontSize = 10.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(400)

    ),
    val labelMediumSB: TextStyle
    = TextStyle(
        fontSize = 10.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(600)
    ),
    val labelSmallSB: TextStyle
    = TextStyle(
        fontSize = 8.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(600),
    ),
    val bodyExtraLarge: TextStyle
    = TextStyle(
        fontSize = 16.sp,
        lineHeight = 22.4.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(400),
    )


)

val CompactSmallTypo = AgroswitTypography(
    headlineLarge = TextStyle(
        fontSize = 20.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(600)
    ),
    headlineMedium = TextStyle(
        fontSize = 20.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(600)
    ),
    headlineSmall = TextStyle(
        fontSize = 18.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(700)
    ),
    titleLarge = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(600)
    ),
    titleSmall = TextStyle(
        fontSize = 12.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(700)
    ),
    titleMedium = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(700)
    ),
    bodyLarge = TextStyle(
        fontSize = 14.sp,
        lineHeight = 19.6.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(400)
    ),
    bodyMedium = TextStyle(
        fontSize = 12.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(400)
    ),
    bodySmall = TextStyle(
        fontSize = 10.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(400)
    ),
    labelLargeM = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(500)

    ),
    labelLargeR = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(400)
    ),
    labelMediumR = TextStyle(
        fontSize = 10.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(400)

    ),
    labelMediumSB = TextStyle(
        fontSize = 10.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(600)
    ),
    labelSmallSB = TextStyle(
        fontSize = 8.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(600),
    ),
    bodyExtraLarge = TextStyle(
        fontSize = 13.sp,
        lineHeight = 20.4.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(400),
    )
)
val CompactMediumTypo = AgroswitTypography(
    headlineLarge = TextStyle(
        fontSize = 28.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(600)
    ),
    headlineMedium = TextStyle(
        fontSize = 20.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(600)
    ),
    headlineSmall = TextStyle(
        fontSize = 18.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(700)
    ),
    titleLarge = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(600)
    ),
    titleSmall = TextStyle(
        fontSize = 12.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(700)
    ),
    titleMedium = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(700)
    ),
    bodyLarge = TextStyle(
        fontSize = 14.sp,
        lineHeight = 19.6.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(400)
    ),
    bodyMedium = TextStyle(
        fontSize = 12.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(400)
    ),
    bodySmall = TextStyle(
        fontSize = 10.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(400)
    ),
    labelLargeM = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(500)

    ),
    labelLargeR = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(400)
    ),
    labelMediumR = TextStyle(
        fontSize = 10.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(400)

    ),
    labelMediumSB = TextStyle(
        fontSize = 10.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(600)
    ),
    labelSmallSB = TextStyle(
        fontSize = 8.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(600),
    ),
    bodyExtraLarge
    = TextStyle(
        fontSize = 18.sp,
        lineHeight = 22.4.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(400),
    )
)

@Composable
fun getAgroswitTypographyWithNonScaledSp(typo: AgroswitTypography): AgroswitTypography {
    return AgroswitTypography(
        headlineLarge = applyNonScaledSpToTextStyle(typo.headlineLarge),
        headlineMedium = applyNonScaledSpToTextStyle(typo.headlineMedium),
        headlineSmall = applyNonScaledSpToTextStyle(typo.headlineSmall),
        titleLarge = applyNonScaledSpToTextStyle(typo.titleLarge),
        titleSmall = applyNonScaledSpToTextStyle(typo.titleSmall),
        titleMedium = applyNonScaledSpToTextStyle(typo.titleMedium),
        bodyLarge = applyNonScaledSpToTextStyle(typo.bodyLarge),
        bodyMedium = applyNonScaledSpToTextStyle(typo.bodyMedium),
        bodySmall = applyNonScaledSpToTextStyle(typo.bodySmall),
        labelLargeM = applyNonScaledSpToTextStyle(typo.labelLargeM),
        labelLargeR = applyNonScaledSpToTextStyle(typo.labelLargeR),
        labelMediumR = applyNonScaledSpToTextStyle(typo.labelMediumR),
        labelMediumSB = applyNonScaledSpToTextStyle(typo.labelMediumSB),
        labelSmallSB = applyNonScaledSpToTextStyle(typo.labelSmallSB)
    )
}


internal val LocalTypography = staticCompositionLocalOf { CompactMediumTypo }