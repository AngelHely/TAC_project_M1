package com.example.legends.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.legends.ui.theme.DarkNavbarColor
import com.example.legends.ui.theme.DarkTextColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavbar(drawerState: DrawerState) {

    val scope = rememberCoroutineScope()
    val iconSize = 24

    androidx.compose.material3.TopAppBar(
        title = { }, modifier = Modifier, navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        drawerState.apply {
                            if (drawerState.isClosed)
                                drawerState.open()
                            else drawerState.close()
                        }
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Navbar Icon",
                    modifier = Modifier.size(iconSize.dp),
                    tint = DarkTextColor,
                )
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings Navbar Icon",
                    modifier = Modifier.size(iconSize.dp),
                    tint = DarkTextColor,
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = DarkNavbarColor
        )
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopDetailBar(navController : NavHostController) {
    TopAppBar(
        title = {},
        navigationIcon = {
        IconButton(onClick = {navController.popBackStack()}) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Retour"
            )
        }
    })
}