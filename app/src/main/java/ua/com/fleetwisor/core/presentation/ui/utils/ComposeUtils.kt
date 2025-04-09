package ua.com.fleetwisor.core.presentation.ui.utils

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle

data class ComposeWrapper(
    val content: @Composable () -> Unit
)


@Composable
fun TextWithHighlightWord(
    text: String,
    keyword: String,
    heightLightStyle: TextStyle,
    heightLightColor: Color,
    color: Color,
    style: TextStyle,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = buildAnnotatedString {
            val spanStyle = SpanStyle(
                color = heightLightColor,
                fontSize = heightLightStyle.fontSize,
                fontWeight = heightLightStyle.fontWeight,
                fontFamily = heightLightStyle.fontFamily,
            )
            var lText = text
            var startIndex = if (keyword.isNotEmpty()) lText.indexOf(keyword) else -1
            while (startIndex >= 0) {
                val endIndex = startIndex + keyword.length
                append(lText.substring(0, startIndex))
                withStyle(style = spanStyle) {
                    append(lText.substring(startIndex, endIndex))
                }
                lText = lText.substring(endIndex)
                startIndex = lText.indexOf(keyword)
            }

            append(lText)
        },
        textAlign = textAlign,
        style = style,
        color = color
    )
}
