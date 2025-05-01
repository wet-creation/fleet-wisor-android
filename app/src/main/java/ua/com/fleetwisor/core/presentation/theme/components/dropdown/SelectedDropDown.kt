package ua.com.fleetwisor.core.presentation.theme.components.dropdown

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ua.com.agroswit.theme.components.select_controls.DropDownItemList
import ua.com.agroswit.theme.components.select_controls.DropDownItemState
import ua.com.fleetwisor.core.domain.utils.Log
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme


@Composable
fun SelectedDropDown(
    modifier: Modifier = Modifier,
    selectedItem: Int,
    displayedItemsCount: Int = 3,
    containerHeight: Dp = 20.dp,
    overlapping: Boolean = true,
    items: () -> List<DropDownItemState>,
    onItemChange: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var itemHeight by remember {
        mutableIntStateOf(0)
    }
    val density = LocalDensity.current
    var widthDp by remember { mutableStateOf(0.dp) }
    LaunchedEffect(widthDp) {
        Log.d("widthDp $widthDp")
    }
    Column(
        modifier = modifier
            .animateContentSize()
            .onGloballyPositioned { coordinates ->
                val widthPx = coordinates.size.width
                widthDp = with(density) { widthPx.toDp() }
            },
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        SelectedDropDownElement(
            textItem = items().firstOrNull {
                it.id == selectedItem
            }?.text ?: "",
            containerHeight = containerHeight,
            expanded = expanded,
            onClick = {
                expanded = !expanded
            }
        )
        if (overlapping)
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = modifier
                    .width(widthDp)
                    .border(
                        width = 1.dp,
                        color = FleetWisorTheme.colors.neutralSecondaryLight,
                        shape = RoundedCornerShape(size = 4.dp)
                    )
                    .background(
                        color = FleetWisorTheme.colors.neutralPrimaryLight,
                        shape = RoundedCornerShape(size = 4.dp)
                    )
                    .padding(horizontal = 4.dp, vertical = 1.dp)
                    .height((itemHeight / 2.0 * displayedItemsCount).dp)


            ) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    items().forEachIndexed { index, item ->
                        DropDownItemList(
                            isActive = item.id == selectedItem,
                            dropDownItemState = item,
                            modifier = Modifier
                                .width(widthDp)
                                .onGloballyPositioned { layoutCoordinates ->
                                    if (itemHeight == 0) {
                                        itemHeight = layoutCoordinates.size.height
                                    }
                                }

                        ) {
                            onItemChange(item.id)
                            expanded = false
                        }
                    }

                }
            }
        else {
            if (expanded) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = FleetWisorTheme.colors.neutralSecondaryLight,
                            shape = RoundedCornerShape(size = 4.dp)
                        )
                        .background(
                            color = FleetWisorTheme.colors.neutralPrimaryLight,
                            shape = RoundedCornerShape(size = 4.dp)
                        )
                        .padding(horizontal = 4.dp, vertical = 1.dp)
                        .height((itemHeight / 2.0 * displayedItemsCount).dp)

                ) {
                    itemsIndexed(items()) { index, item ->
                        DropDownItemList(
                            isActive = item.id == selectedItem,
                            dropDownItemState = item,
                            modifier = Modifier
                                .fillMaxWidth()
                                .onGloballyPositioned { layoutCoordinates ->
                                    if (itemHeight == 0) {
                                        itemHeight = layoutCoordinates.size.height
                                    }
                                }

                        ) {
                            onItemChange(item.id)
                            expanded = false
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun SelectedDropDownElement(
    modifier: Modifier = Modifier,
    textItem: String,
    containerHeight: Dp = 20.dp,
    expanded: Boolean = false,
    active: Boolean = true,
    onClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(containerHeight)
            .border(
                width = 1.dp,
                color = if (expanded) FleetWisorTheme.colors.brandPrimaryNormal else FleetWisorTheme.colors.neutralSecondaryLight,
                shape = RoundedCornerShape(size = 4.dp)
            )
            .background(
                color = if (active) FleetWisorTheme.colors.neutralPrimaryLight else FleetWisorTheme.colors.neutralPrimaryDark,
                shape = RoundedCornerShape(size = 4.dp)
            )
            .padding(horizontal = 4.dp, vertical = 1.dp)
            .then(if (active) Modifier.clickable { onClick() } else Modifier)
            .fillMaxWidth()
    ) {
        Text(
            text = textItem,
            style = FleetWisorTheme.typography.labelLargeR,
            color = FleetWisorTheme.colors.neutralSecondaryDark
        )
        if (active)
            Icon(
                painter = if (expanded) FleetWisorTheme.icons.arrowUp else FleetWisorTheme.icons.arrowDown,
                contentDescription = null,
                tint = FleetWisorTheme.colors.neutralSecondaryDark,
                modifier = Modifier
                    .padding(1.dp)
                    .width(12.dp)
                    .height(12.dp)
            )
    }
}


@Preview
@Composable
private fun SelectedDropDownPrev() {
    val list = listOf(
        DropDownItemState(0, "5l"),
        DropDownItemState(0, "10l"),
        DropDownItemState(0, "20l"),
    )
    val selectedItem = remember {
        mutableIntStateOf(0)
    }
    SelectedDropDown(
        selectedItem = selectedItem.intValue,
        items = {
            list
        },
        onItemChange = {
            selectedItem.intValue = it
        },

        )
}