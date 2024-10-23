package com.example.legends

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.legends.api.Character
import com.example.legends.api.Icon
import com.example.legends.models.CharacterViewModel
import com.example.legends.models.IconViewModel
import com.example.legends.navigation.NavigationMenu
import com.example.legends.navigation.Routes
import com.example.legends.ui.theme.DarkBackgroundColor
import com.example.legends.ui.theme.DarkCharacterCardColor
import com.example.legends.ui.theme.DarkNavbarColor
import com.example.legends.ui.theme.LegendsTheme

class CharactersMenuActivity : ComponentActivity() {

    private val iconViewModel : IconViewModel by viewModels()
    private val characterViewModel : CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LegendsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavigationMenu(modifier = Modifier.padding(innerPadding), context = this) {
                        m, route -> View(m, route.toString())
                    }
                }
            }
        }
    }

    @Composable
    fun View(modifier : Modifier, route: String) {
        val navController = rememberNavController()
        NavHost(navController, route) {
            composable(Routes.List.choice) {
                ViewInList(iconViewModel,navController, modifier)
            }
            composable("${Routes.Details.choice}/{characterID}") {
                val id = it.arguments?.getString("characterID")
                GetCharacterDetails(characterViewModel,navController, modifier, id)
            }
            composable(Routes.LazyVerticalGrid.choice) {
                ViewInLazyVerticalGrid(iconViewModel, navController, modifier)
            }
        }
        Log.d("ROUTE", "${navController.graph.nodes}")

        if (navController.graph.findNode(route) != null) {
            navController.navigate(route)
        } else {
            Log.e("ROUTE", "Route $route does not exist in the graph")
        }
    }
    @Composable
    fun ViewInLazyVerticalGrid(
        vm: IconViewModel,
        navController: NavHostController,
        modifier: Modifier
    ) {
        BackHandler {
            navController.popBackStack()
        }
        LazyVerticalGrid (columns = GridCells.Fixed(2),modifier = modifier) {
            Log.d("SIZE", "${vm.size}")
            items(vm.icons.value) { icon ->
                Card (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                        .clickable { navController.navigate("Details/${icon.id}") },
                    content = {
                        Column(Modifier.padding(16.dp)) {
                            AsyncImage(model= "https://ddragon.leagueoflegends.com/cdn/13.16.1/img/champion/${icon.image.image}" , null)
                        }
                    }
                )

            }
        }
    }

    @Composable
    fun ViewInList(vm: IconViewModel, navController: NavHostController, modifier: Modifier) {
        BackHandler {
            navController.popBackStack()
        }
        LazyColumn(modifier = modifier.background(color = DarkBackgroundColor)) {
            items(vm.icons.value) {
                icon -> CharacterCard(icon, navController)
            }
        }
    }

    @Composable
    fun CharacterCard(icon : Icon, navController: NavHostController) {
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(8.dp)
                .clickable { navController.navigate("Details/${icon.id}") },
            colors = CardDefaults.cardColors(
                containerColor = DarkCharacterCardColor
            ),
            content = {
                Column(Modifier.padding(16.dp)) {
                    AsyncImage(
                        model= "https://ddragon.leagueoflegends.com/cdn/13.16.1/img/champion/${icon.image.image}" ,
                        null,
                        modifier = Modifier.fillMaxHeight()
                    )
                }
            }
        )
    }

    @Composable
    fun GetCharacterDetails(vm : CharacterViewModel, navController: NavHostController, modifier: Modifier, id : String?) {
        BackHandler {
            navController.popBackStack()
        }
        val character = vm.getCharacters(id)
//        Log.d("CHARACTER", "${character}")
        Column(modifier = modifier) {
            AsyncImage(model= "https://ddragon.leagueoflegends.com/cdn/13.16.1/img/champion/${character.image.image}" , null)
        }




    }
}
