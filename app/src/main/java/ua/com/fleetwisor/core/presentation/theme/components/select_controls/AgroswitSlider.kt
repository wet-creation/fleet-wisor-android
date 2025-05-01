package ua.com.agroswit.theme.components.select_controls

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.core.domain.utils.toVolumeString
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgroswitSlider(
    modifier: Modifier = Modifier,
    start: Float,
    end: Float,
    currentValue: Float,
    selected: (Float) -> Unit
) {
    require(start <= end) { "Parameter a should not be greater than parameter b" }
    val totalRange = end - start
    val interactionSource = remember {
        MutableInteractionSource()
    }
    var sliderWidth by remember {
        mutableIntStateOf(0)
    }
    val thumbSize = 32.dp
    var thumbWight by remember {
        mutableIntStateOf(0)
    }
    var sliderPosition by remember { mutableFloatStateOf(currentValue) }
    val interactions = remember { mutableStateListOf<Interaction>() }
    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is PressInteraction.Press -> interactions.add(interaction)
                is PressInteraction.Release -> interactions.remove(interaction.press)
                is PressInteraction.Cancel -> interactions.remove(interaction.press)
                is DragInteraction.Start -> interactions.add(interaction)
                is DragInteraction.Stop -> interactions.remove(interaction.start)
                is DragInteraction.Cancel -> interactions.remove(interaction.start)
            }
        }
    }
    Column(
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier
                .width(40.dp)
                .height(32.dp)
                .offset {
                    IntOffset(
                        x = (((sliderPosition - start) / totalRange * (sliderWidth - thumbWight * 2)) - 5).roundToInt(),
                        y = 0
                    )
                }
                .padding(bottom = 4.dp)


        ) {
            if (interactions.isNotEmpty()) {
                Spacer(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = FleetWisorTheme.colors.brandPrimaryLight,
                            shape = RoundedCornerShape(size = 4.dp)
                        )
                        .fillMaxWidth()
                        .fillMaxHeight()
                )
                Text(
                    text = "%.2f".format(sliderPosition),
                    modifier = Modifier.align(Alignment.Center),
                    style = FleetWisorTheme.typography.titleMedium,
                    color = FleetWisorTheme.colors.brandPrimaryNormal
                )
            }
        }
        CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides Dp.Unspecified) {
            Slider(
                modifier = Modifier.onGloballyPositioned {
                    sliderWidth = it.size.width
                },
                track = {
                    SliderDefaults.Track(
                        modifier = Modifier.scale(scaleY = 0.8f, scaleX = 1f),
                        trackInsideCornerSize = 10.dp,
                        thumbTrackGapSize = 0.dp,
                        colors = SliderDefaults.colors(
                            thumbColor = FleetWisorTheme.colors.brandPrimaryNormal,
                            activeTrackColor = FleetWisorTheme.colors.brandPrimaryNormal,
                            inactiveTrackColor = FleetWisorTheme.colors.neutralPrimaryDark,
                            activeTickColor = Color.Transparent,
                            inactiveTickColor = Color.Transparent,
                            disabledThumbColor = Color.Transparent,
                            disabledActiveTrackColor = Color.Transparent,
                            disabledActiveTickColor = Color.Transparent,
                            disabledInactiveTrackColor = Color.Transparent,
                            disabledInactiveTickColor = Color.Transparent,
                        ),
                        enabled = true,
                        sliderState = it
                    )
                },
                thumb = {
                    Spacer(
                        modifier = modifier
                            .size(thumbSize)
                            .border(
                                shape = CircleShape,
                                width = 2.dp,
                                color = FleetWisorTheme.colors.brandPrimaryNormal
                            )

                            .background(
                                shape = CircleShape,
                                color = FleetWisorTheme.colors.neutralPrimaryLight
                            )
                            .padding(8.dp)
                            .background(
                                shape = CircleShape,
                                color = FleetWisorTheme.colors.brandPrimaryNormal
                            )
                            .onGloballyPositioned {
                                thumbWight = it.size.width
                            },


                        )
                },
                value = sliderPosition,
                onValueChange = { sliderPosition = it },
                valueRange = start..end,
                steps = 10,
                interactionSource = interactionSource,
                onValueChangeFinished = {
                    selected(sliderPosition)
                }


            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = (thumbSize.value / 2f - 10).dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = start.toVolumeString(),
                style = FleetWisorTheme.typography.titleMedium,
                color = FleetWisorTheme.colors.brandPrimaryNormal
            )
            Text(
                text = end.toVolumeString(),
                style = FleetWisorTheme.typography.titleMedium,
                color = FleetWisorTheme.colors.brandPrimaryNormal
            )
        }

    }
}

@Composable
@Preview(showBackground = true)
private fun AgroswitSliderPrev() {
    AgroswitSlider(start = 1.5f, end = 2.8f, currentValue = 2f) {

    }
}