package com.example.legends.navigation.iconButtonNav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.legends.mvvm.viewModels.IconViewModel

@Composable
fun FavoriteButtonMode(
    iconVM: IconViewModel,
    navController: NavHostController
) {
    IconButton(onClick = {
        iconVM.toggleFavoriteMode()
        navController.navigate(navController.currentDestination?.route.toString())
    }) {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "Column",
            tint =
            if (iconVM.getFavoriteMode()) Color.Yellow
            else Color.White
        )
    }
}