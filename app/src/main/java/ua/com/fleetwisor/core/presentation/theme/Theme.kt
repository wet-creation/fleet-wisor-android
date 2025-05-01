package ua.com.fleetwisor.core.presentation.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import ua.com.agroswit.theme.AgroswitDimensions
import ua.com.agroswit.theme.AgroswitTypography
import ua.com.agroswit.theme.LocalDimensions
import ua.com.agroswit.theme.LocalTypography

internal val LightColorScheme = AgroswitColor(
    brandPrimaryNormal = LightBrandPrimaryNormal,
    brandPrimaryExtraLight = LightBrandPrimaryExtraLight,
    brandPrimaryLight = LightBrandPrimaryLight,
    brandPrimaryMedium = LightBrandPrimaryMedium,
    brandPrimaryDark = LightBrandPrimaryDark,
    brandSecondaryNormal = LightBrandSecondaryNormal,
    neutralPrimaryNormal = LightNeutralPrimaryNormal,
    neutralPrimaryLight = LightNeutralPrimaryLight,
    neutralPrimaryDark = LightNeutralPrimaryDark,
    neutralSecondaryNormal = LightNeutralSecondaryNormal,
    neutralSecondaryLight = LightNeutralSecondaryLight,
    neutralSecondaryDark = LightNeutralSecondaryDark,
    errorNormal = LightErrorNormal,
    errorLight = LightErrorLight,
    errorDark = LightErrorDark,
    neutralSecondaryMedium = LightNeutralSecondaryMedium,
    herbicideColor = LightHerbicideColor,
    desiccantColor = LightDesiccantColor,
    insecticideColor = LightInsecticideColor,
    poisonerColor = LightPoisonerColor,
    fungicideColor = LightFungicideColor,
    microFertilizerColor = LightMicroFertilizerColor,
    bioProductColor = LightBioProductColor,
    labelToDo = LightLabelTodo,
    labelInProgress = LightLabelInProgress
)

internal val LocalColors = compositionLocalOf { LightColorScheme }

@Composable
fun FleetWisorTheme(
    colors: AgroswitColor = FleetWisorTheme.colors,
    typography: AgroswitTypography = FleetWisorTheme.typography,
    dimensions: AgroswitDimensions = FleetWisorTheme.dimensions,
    icons: AgroswitIcon = FleetWisorTheme.icons,
    content: @Composable () -> Unit,
) {
    val rememberedColors = remember { colors.copy() }.apply { updateColors(colors) }


    CompositionLocalProvider(
        LocalColors provides rememberedColors,
        LocalDimensions provides dimensions,
        LocalTypography provides typography,
        LocaleIcon provides icons,
        LocalIndication provides ripple(),
    ) {
        content()
    }
}

object FleetWisorTheme {

    val colors: AgroswitColor
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: AgroswitTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val dimensions: AgroswitDimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalDimensions.current

    val icons: AgroswitIcon
        @Composable
        @ReadOnlyComposable
        get() = LocaleIcon.current
}