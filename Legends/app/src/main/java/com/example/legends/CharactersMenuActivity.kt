package com.example.legends

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.legends.models.IconViewModel
import com.example.legends.navigation.NavigationMenu
import com.example.legends.navigation.Routes
import com.example.legends.ui.theme.LegendsTheme

class CharactersMenuActivity : ComponentActivity() {

    private val iconViewModel : IconViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LegendsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavigationMenu(modifier = Modifier.padding(innerPadding), context = this) {
                        _, m, route -> View(iconViewModel, m, route.toString())
                    }
                }
            }
        }
    }

    @Composable
    fun View(vm : IconViewModel, modifier : Modifier, route: String) {
        val navController = rememberNavController()
        NavHost(navController, Routes.LazyVerticalGrid.choice) {
            composable(Routes.LazyVerticalGrid.choice) { ViewInLazyVerticalGrid(vm, modifier)
            composable(Routes.List.choice) {ViewInList(vm,modifier)}
            }
        }
        Log.d("ROUTE", route)
        if (route != "") {
            navController.navigate(route)
        }
    }
    @Composable
    fun ViewInLazyVerticalGrid(vm : IconViewModel, modifier : Modifier) {
        LazyVerticalGrid (columns = GridCells.Fixed(2),modifier = modifier) {
            Log.d("SIZE", "${vm.size}")
            items(vm.icons.value) { icon ->
                Card (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    content = { Column(Modifier.padding(16.dp)) {AsyncImage(model= "https://ddragon.leagueoflegends.com/cdn/13.16.1/img/champion/${icon.image}" , null)} }
                )

            }
        }
    }

    @Composable
    fun ViewInList(vm : IconViewModel, modifier: Modifier) {
        LazyColumn(modifier = modifier) {
            items(vm.icons.value) {
                icon ->
                Card (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    content = { Column(Modifier.padding(16.dp)) {AsyncImage(model= "https://ddragon.leagueoflegends.com/cdn/13.16.1/img/champion/${icon.image}" , null)} }
                )
            }
        }
    }
}
