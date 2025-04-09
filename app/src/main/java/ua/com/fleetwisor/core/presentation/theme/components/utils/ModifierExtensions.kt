package ua.com.agroswit.theme.components.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    this.clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}


fun Modifier.partialBorder(
    color: Color,
    strokeWidth: Dp,
    cornerRadiusTopStart: Dp = 0.dp,
    cornerRadiusTopEnd: Dp = 0.dp,
    cornerRadiusBottomEnd: Dp = 0.dp,
    cornerRadiusBottomStart: Dp = 0.dp,
    drawLeft: Boolean = false,
    drawTop: Boolean = false,
    drawRight: Boolean = false,
    drawBottom: Boolean = false
): Modifier {
    return this.drawBehind {
        val stroke = strokeWidth.toPx()
        val width = size.width
        val height = size.height
        val halfStroke = stroke / 2

        // Конвертируем радиусы в пиксели
        val topStartRadius = cornerRadiusTopStart.toPx()
        val topEndRadius = cornerRadiusTopEnd.toPx()
        val bottomEndRadius = cornerRadiusBottomEnd.toPx()
        val bottomStartRadius = cornerRadiusBottomStart.toPx()

        val paint = Paint().apply {
            this.color = color
            this.strokeWidth = stroke
            this.style = PaintingStyle.Stroke
            this.isAntiAlias = true
            this.strokeCap = StrokeCap.Round
            this.strokeJoin = StrokeJoin.Round
        }

        val path = Path().apply {
            if (drawLeft) {
                moveTo(halfStroke, height - bottomStartRadius)
                arcTo(
                    rect = Rect(0f, height - bottomStartRadius * 2, bottomStartRadius * 2, height),
                    startAngleDegrees = 90f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )
                lineTo(halfStroke, topStartRadius)
                arcTo(
                    rect = Rect(0f, 0f, topStartRadius * 2, topStartRadius * 2),
                    startAngleDegrees = 180f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )
            }
            if (drawTop) {
                moveTo(topStartRadius, halfStroke)
                lineTo(width - topEndRadius, halfStroke)
                arcTo(
                    rect = Rect(width - topEndRadius * 2, 0f, width, topEndRadius * 2),
                    startAngleDegrees = -90f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )
            }
            if (drawRight) {
                moveTo(width - halfStroke, topEndRadius)
                lineTo(width - halfStroke, height - bottomEndRadius)
                arcTo(
                    rect = Rect(width - bottomEndRadius * 2, height - bottomEndRadius * 2, width, height),
                    startAngleDegrees = 0f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )
            }
            if (drawBottom) {
                moveTo(bottomStartRadius, height - halfStroke)
                lineTo(width - bottomEndRadius, height - halfStroke)
                arcTo(
                    rect = Rect(0f, height - bottomStartRadius * 2, bottomStartRadius * 2, height),
                    startAngleDegrees = 90f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )
            }
        }

        drawContext.canvas.drawPath(path, paint)
    }
}


