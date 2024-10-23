package com.example.legends.navigation

import android.annotation.SuppressLint
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
import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.IconButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.compose.rememberNavController
import com.example.legends.CharactersMenuActivity
import com.example.legends.MainActivity
import com.example.legends.ui.theme.DarkDisabledTextColor
import com.example.legends.ui.theme.DarkTextColor


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationMenu(modifier: Modifier = Modifier, context: ComponentActivity, view: @Composable (Modifier, Any?) -> Unit) {

    val charactersMenuActivityIntent = Intent(context, CharactersMenuActivity::class.java)
    var currentRoute by remember { mutableStateOf(Routes.List.choice) }

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

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
                    onClick = { if (context !is MainActivity) {
                        context.finish()
                    } },
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
                    onClick = { (context as? Activity)?.startActivity(charactersMenuActivityIntent)
                        if (context !is MainActivity) {
                            context.finish()
                        }},
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
                TopNavbar(drawerState = drawerState)
            },
            bottomBar = {
                BottomAppBar(
                    modifier = Modifier.height(if (isLandscape) 60.dp else 105.dp),
                    containerColor = DarkNavbarColor,
                    actions = {

                        Row (
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            IconButton(onClick = { currentRoute = Routes.List.choice }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.List,
                                    contentDescription = "List",
                                    tint =
                                        if (currentRoute == Routes.List.choice) DarkTextColor
                                        else DarkDisabledTextColor,
                                )
                            }
                            Spacer(modifier = Modifier.width(175.dp))
                            IconButton(onClick = { currentRoute = Routes.LazyVerticalGrid.choice }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Column",
                                    tint =
                                        if (currentRoute == Routes.LazyVerticalGrid.choice) DarkTextColor
                                        else DarkDisabledTextColor,
                                )
                            }
                        }

                })
            }
        ) { innerPadding ->
            view(Modifier.padding(innerPadding).fillMaxWidth(), currentRoute)
        }
    }
}
