package com.blue.designsystem.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun RowScope.MandaNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: Int,
    modifier: Modifier = Modifier,
    selectedIcon: Int = icon,
    label: String,
    ) {
    NavigationBarItem(
        selected = selected,
        onClick = { onClick() },
        icon = {
            Icon(
                modifier = modifier.size(30.dp),
                painter = painterResource(id = if(selected) selectedIcon else icon),
                contentDescription = null
            )
        },
        modifier = modifier,
        label = { Text(text = label) },
        alwaysShowLabel = true,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Color.DarkGray,
            unselectedIconColor = Color.LightGray,
            selectedTextColor = Color.DarkGray,
            unselectedTextColor = Color.LightGray,
            indicatorColor = Color.Gray,
        )
    )

}
