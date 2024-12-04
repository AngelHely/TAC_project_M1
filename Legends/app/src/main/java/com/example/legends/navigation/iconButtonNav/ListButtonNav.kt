package com.example.legends.navigation.iconButtonNav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.legends.navigation.Routes
import com.example.legends.ui.theme.DarkDisabledTextColor
import com.example.legends.ui.theme.DarkTextColor

@Composable
fun ListButtonNav(navController: NavHostController) {
    IconButton(onClick = {
        if (navController.currentDestination?.route.toString() != Routes.List.choice)
            navController.navigate(Routes.List.choice)
    })
    {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.List,
            contentDescription = "List",
            tint =
            if (navController.currentDestination?.route.toString() == Routes.List.choice) DarkTextColor
            else DarkDisabledTextColor,
        )
    }
}