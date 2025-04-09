package ua.com.fleetwisor.core.presentation.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import ua.com.agroswit.theme.AgroswitColor
import ua.com.agroswit.theme.AgroswitDimensions
import ua.com.agroswit.theme.AgroswitTypography
import ua.com.agroswit.theme.LightBioProductColor
import ua.com.agroswit.theme.LightBrandPrimaryDark
import ua.com.agroswit.theme.LightBrandPrimaryExtraLight
import ua.com.agroswit.theme.LightBrandPrimaryLight
import ua.com.agroswit.theme.LightBrandPrimaryMedium
import ua.com.agroswit.theme.LightBrandPrimaryNormal
import ua.com.agroswit.theme.LightBrandSecondaryNormal
import ua.com.agroswit.theme.LightDesiccantColor
import ua.com.agroswit.theme.LightErrorDark
import ua.com.agroswit.theme.LightErrorLight
import ua.com.agroswit.theme.LightErrorNormal
import ua.com.agroswit.theme.LightFungicideColor
import ua.com.agroswit.theme.LightHerbicideColor
import ua.com.agroswit.theme.LightInsecticideColor
import ua.com.agroswit.theme.LightLabelInProgress
import ua.com.agroswit.theme.LightLabelTodo
import ua.com.agroswit.theme.LightMicroFertilizerColor
import ua.com.agroswit.theme.LightNeutralPrimaryDark
import ua.com.agroswit.theme.LightNeutralPrimaryLight
import ua.com.agroswit.theme.LightNeutralPrimaryNormal
import ua.com.agroswit.theme.LightNeutralSecondaryDark
import ua.com.agroswit.theme.LightNeutralSecondaryLight
import ua.com.agroswit.theme.LightNeutralSecondaryMedium
import ua.com.agroswit.theme.LightNeutralSecondaryNormal
import ua.com.agroswit.theme.LightPoisonerColor
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