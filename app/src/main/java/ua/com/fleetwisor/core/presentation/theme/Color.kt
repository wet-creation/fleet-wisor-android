package ua.com.fleetwisor.core.presentation.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

internal val LightBrandPrimaryNormal = Color(0xFF1E3A5F)
internal val LightBrandPrimaryExtraLight = Color(0xFFD6E4F0)
internal val LightBrandPrimaryLight = Color(0xFF7BA0C0)
internal val LightBrandPrimaryMedium = Color(0xFF3E658F)
internal val LightBrandPrimaryDark = Color(0xFF162D4A)
internal val LightBrandSecondaryNormal = Color(0xFFF5A623)
internal val LightNeutralPrimaryNormal = Color(0xFFF8F9FA)
internal val LightNeutralPrimaryLight = Color(0xFFFFFFFF)
internal val LightNeutralPrimaryDark = Color(0xFFE4E7EB)
internal val LightNeutralSecondaryNormal = Color(0xFFB0B8C5)
internal val LightNeutralSecondaryLight = Color(0xFFD6DBE0)
internal val LightNeutralSecondaryMedium = Color(0xFF6C757D)
internal val LightNeutralSecondaryDark = Color(0xFF212529)
internal val LightErrorNormal = Color(0xFFD9534F)
internal val LightErrorLight = Color(0xFFF8D7DA)
internal val LightErrorDark = Color(0xFFC82333)

internal val LightHerbicideColor = Color(0xFF388E3C)
internal val LightDesiccantColor = Color(0xFFFFC107)
internal val LightInsecticideColor = Color(0xFFC62828)
internal val LightPoisonerColor = Color(0xFFDCDCDC)
internal val LightFungicideColor = Color(0xFF1565C0)
internal val LightMicroFertilizerColor = Color(0xFF00897B)
internal val LightBioProductColor = Color(0xFF4CAF50)

internal val LightLabelTodo = Color(0xFFFF720D)
internal val LightLabelInProgress = Color(0xFF2263E2)


class AgroswitColor(
    brandPrimaryNormal: Color,
    brandPrimaryExtraLight: Color,
    brandPrimaryLight: Color,
    brandPrimaryMedium: Color,
    brandPrimaryDark: Color,
    brandSecondaryNormal: Color,
    neutralPrimaryNormal: Color,
    neutralPrimaryLight: Color,
    neutralPrimaryDark: Color,
    neutralSecondaryNormal: Color,
    neutralSecondaryLight: Color,
    neutralSecondaryDark: Color,
    errorNormal: Color,
    errorLight: Color,
    errorDark: Color,
    neutralSecondaryMedium: Color,
    herbicideColor: Color,
    desiccantColor: Color,
    insecticideColor: Color,
    poisonerColor: Color,
    fungicideColor: Color,
    microFertilizerColor: Color,
    bioProductColor: Color,
    labelToDo: Color,
    labelInProgress: Color,
) {
    var brandPrimaryNormal by mutableStateOf(brandPrimaryNormal)
        private set
    var brandPrimaryExtraLight by mutableStateOf(brandPrimaryExtraLight)
        private set
    var brandPrimaryLight by mutableStateOf(brandPrimaryLight)
        private set
    var brandPrimaryMedium by mutableStateOf(brandPrimaryMedium)
        private set
    var brandPrimaryDark by mutableStateOf(brandPrimaryDark)
        private set
    var brandSecondaryNormal by mutableStateOf(brandSecondaryNormal)
        private set
    var neutralPrimaryNormal by mutableStateOf(neutralPrimaryNormal)
        private set
    var neutralPrimaryLight by mutableStateOf(neutralPrimaryLight)
        private set
    var neutralPrimaryDark by mutableStateOf(neutralPrimaryDark)
        private set
    var neutralSecondaryNormal by mutableStateOf(neutralSecondaryNormal)
        private set
    var neutralSecondaryLight by mutableStateOf(neutralSecondaryLight)
        private set
    var neutralSecondaryDark by mutableStateOf(neutralSecondaryDark)
        private set
    var errorNormal by mutableStateOf(errorNormal)
        private set
    var errorDark by mutableStateOf(errorDark)
        private set
    var errorLight by mutableStateOf(errorLight)
        private set
    var neutralSecondaryMedium by mutableStateOf(neutralSecondaryMedium)
        private set
    var herbicideColor by mutableStateOf(herbicideColor)
        private set
    var fungicideColor by mutableStateOf(fungicideColor)
        private set
    var poisonerColor by mutableStateOf(poisonerColor)
        private set
    var insecticideColor by mutableStateOf(insecticideColor)
        private set
    var desiccantColor by mutableStateOf(desiccantColor)
        private set
    var bioProductColor by mutableStateOf(bioProductColor)
        private set
    var microFertilizerColor by mutableStateOf(microFertilizerColor)
        private set


    var labelToDo by mutableStateOf(labelToDo)
        private set
    var labelInProgress by mutableStateOf(labelInProgress)
        private set

    fun copy(
        brandPrimaryNormal: Color = this.brandPrimaryNormal,
        brandPrimaryExtraLight: Color = this.brandPrimaryExtraLight,
        brandPrimaryLight: Color = this.brandPrimaryLight,
        brandPrimaryMedium: Color = this.brandPrimaryMedium,
        brandPrimaryDark: Color = this.brandPrimaryDark,
        brandSecondaryNormal: Color = this.brandSecondaryNormal,
        neutralPrimaryNormal: Color = this.neutralPrimaryNormal,
        neutralPrimaryLight: Color = this.neutralPrimaryLight,
        neutralPrimaryDark: Color = this.neutralPrimaryDark,
        neutralSecondaryNormal: Color = this.neutralSecondaryNormal,
        neutralSecondaryLight: Color = this.neutralSecondaryLight,
        neutralSecondaryDark: Color = this.neutralSecondaryDark,
        errorNormal: Color = this.errorNormal,
        errorLight: Color = this.errorLight,
        errorDark: Color = this.errorDark,
        neutralSecondaryMedium: Color = this.neutralSecondaryMedium,
        herbicideColor: Color = this.herbicideColor,
        fungicideColor: Color = this.fungicideColor,
        poisonerColor: Color = this.poisonerColor,
        insecticideColor: Color = this.insecticideColor,
        desiccantColor: Color = this.desiccantColor,
        bioProductColor: Color = this.bioProductColor,
        microFertilizerColor: Color = this.microFertilizerColor,
        labelToDo: Color = this.labelToDo,
        labelInProgress: Color = this.labelInProgress,

        ): AgroswitColor {
        return AgroswitColor(
            brandPrimaryNormal = brandPrimaryNormal,
            brandPrimaryExtraLight = brandPrimaryExtraLight,
            brandPrimaryLight = brandPrimaryLight,
            brandPrimaryMedium = brandPrimaryMedium,
            brandPrimaryDark = brandPrimaryDark,
            brandSecondaryNormal = brandSecondaryNormal,
            neutralPrimaryNormal = neutralPrimaryNormal,
            neutralPrimaryLight = neutralPrimaryLight,
            neutralPrimaryDark = neutralPrimaryDark,
            neutralSecondaryNormal = neutralSecondaryNormal,
            neutralSecondaryLight = neutralSecondaryLight,
            neutralSecondaryDark = neutralSecondaryDark,
            errorNormal = errorNormal,
            errorLight = errorLight,
            errorDark = errorDark,
            neutralSecondaryMedium = neutralSecondaryMedium,
            herbicideColor = herbicideColor,
            fungicideColor = fungicideColor,
            poisonerColor = poisonerColor,
            insecticideColor = insecticideColor,
            desiccantColor = desiccantColor,
            bioProductColor = bioProductColor,
            microFertilizerColor = microFertilizerColor,
            labelToDo = labelToDo,
            labelInProgress = labelInProgress,
        )
    }

    fun updateColors(other: AgroswitColor) {
        brandPrimaryNormal = other.brandPrimaryNormal
        brandPrimaryExtraLight = other.brandPrimaryExtraLight
        brandPrimaryLight = other.brandPrimaryLight
        brandPrimaryMedium = other.brandPrimaryMedium
        brandPrimaryDark = other.brandPrimaryDark
        brandSecondaryNormal = other.brandSecondaryNormal
        neutralPrimaryNormal = other.neutralPrimaryNormal
        neutralPrimaryLight = other.neutralPrimaryLight
        neutralPrimaryDark = other.neutralPrimaryDark
        neutralSecondaryNormal = other.neutralSecondaryNormal
        neutralSecondaryLight = other.neutralSecondaryLight
        neutralSecondaryDark = other.neutralSecondaryDark
        errorNormal = other.errorNormal
        errorLight = other.errorLight
        errorDark = other.errorDark
        neutralSecondaryMedium = other.neutralSecondaryMedium
        herbicideColor = other.herbicideColor
        fungicideColor = other.fungicideColor
        poisonerColor = other.poisonerColor
        insecticideColor = other.insecticideColor
        desiccantColor = other.desiccantColor
        microFertilizerColor = other.microFertilizerColor
        bioProductColor = other.bioProductColor
        labelToDo = other.labelToDo
        labelInProgress = other.labelInProgress
    }
}


