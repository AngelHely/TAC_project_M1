package com.example.legends.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.legends.mvvm.viewModels.IconViewModel
import com.example.legends.navigation.iconButtonNav.FavoriteButtonMode
import com.example.legends.navigation.iconButtonNav.GridButtonNav
import com.example.legends.navigation.iconButtonNav.ListButtonNav
import com.example.legends.ui.theme.DarkDisabledTextColor
import com.example.legends.ui.theme.DarkNavbarColor
import com.example.legends.ui.theme.DarkTextColor

@Composable
fun BottomNavBar(
    isLandscape: Boolean,
    navController: NavHostController,
    iconVM: IconViewModel
) {
    BottomAppBar(
        modifier = Modifier.height(if (isLandscape) 60.dp else 105.dp),
        containerColor = DarkNavbarColor,
        actions = {

            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                ListButtonNav(navController)
                Spacer(modifier = Modifier.width(75.dp))
                GridButtonNav(navController)
                Spacer(modifier = Modifier.width(75.dp))
                FavoriteButtonMode(iconVM, navController)
            }

        })
}