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
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.IconButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.example.legends.CharactersMenuActivity
import com.example.legends.MainActivity


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationMenu(modifier: Modifier = Modifier, context: ComponentActivity, view: @Composable (Modifier, Any?) -> Unit) {

    val charactersMenuActivityIntent = Intent(context, CharactersMenuActivity::class.java)
    var currentRoute by remember { mutableStateOf(Routes.LazyVerticalGrid.choice) }

    val itemColors =  NavigationDrawerItemDefaults.colors(
        unselectedContainerColor = Color.Transparent,
        unselectedIconColor = Color.White,
        selectedIconColor = Color.White,
        unselectedTextColor = Color.White,
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
                    color = Color.Gray,
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
                BottomAppBar(actions = {
                    Row (modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center) {
                        IconButton(onClick = { currentRoute = Routes.List.choice }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.List,
                                contentDescription = "List"
                            )
                        }
                        Spacer(modifier = Modifier.width(175.dp))
                        IconButton(onClick = { currentRoute = Routes.LazyVerticalGrid.choice }) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = "Column")
                        }
                    }
                })
            }
        ) { innerPadding ->
            view(Modifier.padding(innerPadding), currentRoute)
        }
    }
}
