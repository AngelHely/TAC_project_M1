package com.example.legends.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.legends.ui.theme.DarkNavbarColor
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalConfiguration
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.legends.GetCharacterDetails
import com.example.legends.LegendApplication
import com.example.legends.ViewInLazyVerticalGrid
import com.example.legends.ViewInList
import com.example.legends.ui.theme.DarkDisabledTextColor
import com.example.legends.ui.theme.DarkTextColor
import com.example.legends.mvvm.viewModels.CharacterViewModelFactory
import com.example.legends.mvvm.viewModels.IconViewModel
import com.example.legends.mvvm.viewModels.IconViewModelFactory
import okhttp3.Route


@Composable
fun NavigationMenu(
    modifier: Modifier = Modifier,
    app: LegendApplication
) {

    // NavController management
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination?.route


    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    val iconVM: IconViewModel = viewModel(factory = IconViewModelFactory(app.iconUseCase))

    val itemColors =  NavigationDrawerItemDefaults.colors(
        unselectedContainerColor = Color.Transparent,
        unselectedIconColor = DarkTextColor,
        selectedIconColor = DarkTextColor,
        unselectedTextColor = DarkTextColor,
    )

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet (
                modifier = modifier
                    .width(250.dp),
                drawerContainerColor = DarkNavbarColor

            ) {
                NavigationDrawerItem(
                    label = { Text(text = "Accueil") },
                    selected = false,
                    onClick = { },
                    icon = { Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Navigation drawer : home",
                        modifier = Modifier.size(20.dp),
                    ) },
                    colors = itemColors,
                )
                HorizontalDivider(
                    color = DarkTextColor,
                )
                NavigationDrawerItem(
                    label = { Text(text = "Personnages") },
                    selected = false,
                    onClick = {},
                    icon = { Icon(
                        imageVector = Icons.Default.AccountBox,
                        contentDescription = "Navigation drawer : characters",
                        modifier = Modifier.size(20.dp),
                    ) },
                    colors = itemColors,
                )
            }
        },
    ) {
        Scaffold(
            topBar = {
                if (currentDestination != null && currentDestination.startsWith(Routes.Details.choice)) {
                    TopDetailBar(navController)
                }
                else {
                    TopNavbar(drawerState = drawerState)
                }
            },
            bottomBar = {
                if (currentDestination != null && !currentDestination.startsWith(Routes.Details.choice)) {
                    BottomNavBar(isLandscape, navController, iconVM)
                }
            }
        ) { innerPadding ->
            NavHost(navController, startDestination = Routes.List.choice) {
                composable(Routes.List.choice) {
                    ViewInList(iconVM,navController, Modifier.padding(innerPadding))
                }
                composable(Routes.LazyVerticalGrid.choice) {
                    ViewInLazyVerticalGrid(iconVM, navController, Modifier.padding(innerPadding))
                }
                composable("${Routes.Details.choice}/{characterID}") {
                    val id = it.arguments?.getString("characterID")?:throw Error("error id details")
                    GetCharacterDetails(viewModel(factory =  CharacterViewModelFactory(app.characterUseCase)),navController,Modifier.padding(innerPadding), id)
                }
            }
        }
    }
}


