package ua.com.agroswit.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class AgroswitDimensions(
    val spacing0: Dp = 0.dp,
    val spacing1: Dp = 4.dp,
    val spacing2: Dp = 8.dp,
    val spacing3: Dp = 12.dp,
    val spacing4: Dp = 16.dp,
    val spacing5: Dp = 20.dp,
    val spacing6: Dp = 24.dp,
    val spacing7: Dp = 28.dp,
    val spacing8: Dp = 32.dp,
    val spacing9: Dp = 36.dp,
    val spacing10: Dp = 40.dp,
    val spacing11: Dp = 44.dp,
    val spacing12: Dp = 48.dp,
    val defaultButtonHeight: Dp = 40.dp,
    val largeButtonSize: Dp = 150.dp,
    val secondButtonHeight: Dp = 48.dp,
    val onboardingImageSize: Dp = 320.dp

)

val CompactSmall = AgroswitDimensions(
    spacing0 = 0.dp,
    spacing1 = 4.dp,
    spacing2 = 8.dp,
    spacing3 = 12.dp,
    spacing4 = 16.dp,
    spacing5 = 15.dp,
    spacing6 = 24.dp,
    spacing7 = 28.dp,
    spacing8 = 28.dp,
    spacing9 = 36.dp,
    spacing10 = 40.dp,
    spacing11 = 44.dp,
    spacing12 = 48.dp,
    defaultButtonHeight = 30.dp,
    secondButtonHeight = 38.dp,
    largeButtonSize = 138.dp,
    onboardingImageSize = 240.dp
)
val CompactMedium = AgroswitDimensions(
    spacing0 = 0.dp,
    spacing1 = 4.dp,
    spacing2 = 8.dp,
    spacing3 = 12.dp,
    spacing4 = 16.dp,
    spacing5 = 20.dp,
    spacing6 = 24.dp,
    spacing7 = 28.dp,
    spacing8 = 32.dp,
    spacing9 = 36.dp,
    spacing10 = 40.dp,
    spacing11 = 44.dp,
    spacing12 = 48.dp
)
val Compact = AgroswitDimensions(
    spacing0 = 0.dp,
    spacing1 = 4.dp,
    spacing2 = 8.dp,
    spacing3 = 12.dp,
    spacing4 = 16.dp,
    spacing5 = 20.dp,
    spacing6 = 24.dp,
    spacing7 = 28.dp,
    spacing8 = 32.dp,
    spacing9 = 36.dp,
    spacing10 = 40.dp,
    spacing11 = 44.dp,
    spacing12 = 48.dp
)
val Medium = AgroswitDimensions(
    spacing0 = 0.dp,
    spacing1 = 4.dp,
    spacing2 = 8.dp,
    spacing3 = 12.dp,
    spacing4 = 16.dp,
    spacing5 = 20.dp,
    spacing6 = 24.dp,
    spacing7 = 28.dp,
    spacing8 = 32.dp,
    spacing9 = 36.dp,
    spacing10 = 40.dp,
    spacing11 = 44.dp,
    spacing12 = 48.dp,
    onboardingImageSize = 480.dp
)
val Expanded = AgroswitDimensions(
    spacing0 = 0.dp,
    spacing1 = 4.dp,
    spacing2 = 8.dp,
    spacing3 = 12.dp,
    spacing4 = 16.dp,
    spacing5 = 20.dp,
    spacing6 = 24.dp,
    spacing7 = 28.dp,
    spacing8 = 32.dp,
    spacing9 = 36.dp,
    spacing10 = 40.dp,
    spacing11 = 44.dp,
    spacing12 = 48.dp
)

internal val LocalDimensions = staticCompositionLocalOf { CompactMedium }