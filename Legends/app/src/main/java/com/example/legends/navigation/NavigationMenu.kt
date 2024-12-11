package com.example.legends.navigation

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.legends.GetCharacterDetails
import com.example.legends.LegendApplication
import com.example.legends.ViewInLazyVerticalGrid
import com.example.legends.ViewInList
import com.example.legends.mvvm.viewModels.CharacterViewModelFactory
import com.example.legends.mvvm.viewModels.IconViewModel
import com.example.legends.mvvm.viewModels.IconViewModelFactory
import com.example.legends.ui.theme.DarkBackgroundColor


@Composable
fun NavigationMenu(
    app: LegendApplication,
) {

    // NavController management
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination?.route


    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    val iconVM: IconViewModel = viewModel(factory = IconViewModelFactory(app.iconUseCase))

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {},
    ) {
        Scaffold( containerColor = DarkBackgroundColor,
            topBar = {
                if (currentDestination != null && currentDestination.startsWith(Routes.Details.choice)) {
                    TopDetailBar(navController)
                }
                else {
                    TopNavbar()
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


