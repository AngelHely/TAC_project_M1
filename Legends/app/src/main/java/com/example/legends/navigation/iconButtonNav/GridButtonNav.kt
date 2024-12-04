package com.example.legends.navigation.iconButtonNav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.legends.navigation.Routes
import com.example.legends.ui.theme.DarkDisabledTextColor
import com.example.legends.ui.theme.DarkTextColor

@Composable
fun GridButtonNav(navController: NavHostController) {
    IconButton(onClick = {
        if (navController.currentDestination?.route.toString() != Routes.LazyVerticalGrid.choice)
            navController.navigate(Routes.LazyVerticalGrid.choice)
    }) {
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Column",
            tint =
            if (navController.currentDestination?.route.toString() == Routes.LazyVerticalGrid.choice) DarkTextColor
            else DarkDisabledTextColor,
        )
    }
}