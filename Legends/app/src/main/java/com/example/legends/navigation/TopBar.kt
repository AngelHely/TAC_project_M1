package com.example.legends.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.legends.ui.theme.DarkNavbarColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavbar() {

    TopAppBar(
        title = { Text("Legends Application", color = Color.White) },
        colors = topAppBarColors(
        containerColor = DarkNavbarColor)
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopDetailBar(navController : NavHostController) {
    TopAppBar(
        title = {},
        colors = topAppBarColors(containerColor = DarkNavbarColor),
        navigationIcon = {
        IconButton(onClick = {navController.popBackStack()}) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Retour",
                tint = Color.White
            )
        }
    })
}