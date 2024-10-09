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
import com.example.legends.CharactersMenuActivity
import com.example.legends.MainActivity


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationMenu(modifier: Modifier = Modifier, context: ComponentActivity, view : @Composable () -> Unit) {

    val charactersMenuActivityIntent = Intent(context, CharactersMenuActivity::class.java)

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
            }
        ) {
            view()
        }
    }
}
