package com.example.legends

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.legends.api.models.Icon
import com.example.legends.mvvm.viewModels.CharacterViewModel
import com.example.legends.mvvm.viewModels.IconViewModel
import com.example.legends.navigation.NavigationMenu
import com.example.legends.ui.theme.DarkBackgroundColor
import com.example.legends.ui.theme.DarkCharacterCardColor
import com.example.legends.ui.theme.LegendsTheme

class CharactersMenuActivity : ComponentActivity() {

    private lateinit var app : LegendApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        app = LegendApplication()
        app.setContext(this)
        setContent {
            LegendsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavigationMenu(modifier = Modifier.padding(innerPadding), app)
                }
            }
        }
    }
}

@Composable
fun ViewInLazyVerticalGrid(
    vm: IconViewModel,
    navController: NavHostController,
    modifier: Modifier,
) {
    BackHandler {
        navController.popBackStack()
    }
    LazyVerticalGrid (columns = GridCells.Fixed(2),modifier = modifier.background(color = DarkBackgroundColor)) {
        val charactersList = getIconsList(vm)
        items(charactersList) {
                icon -> CharacterCard(icon, navController)
        }
    }
}

@Composable
fun ViewInList(
    vm: IconViewModel,
    navController: NavHostController,
    modifier: Modifier,
) {
    BackHandler {
        navController.popBackStack()
    }
    LazyColumn(modifier = modifier.background(color = DarkBackgroundColor)) {
        val charactersList = getIconsList(vm)
        items(charactersList) {
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
                    model= "https://ddragon.leagueoflegends.com/cdn/13.16.1/img/champion/${icon.id}.png" ,
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
    val character = vm.getCharacter(id)
    Column(modifier = modifier.background(color = DarkCharacterCardColor).fillMaxSize().verticalScroll(rememberScrollState())) {
        Row (modifier = Modifier.padding(20.dp), horizontalArrangement = Arrangement.Center){
            AsyncImage(
                model = "https://ddragon.leagueoflegends.com/cdn/13.16.1/img/champion/${character.image.image}",
                contentDescription = null,
                modifier = Modifier.size(125.dp)
            )
            Spacer(modifier = Modifier.padding(horizontal = 10.dp))
            Column(){
                Text(text = character.id.toString(), color = Color.White, fontSize = 40.sp)
                Text(text = character.title, color = Color.White, fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.padding(horizontal = 10.dp))
            character.id?.let { FavoriteButton(vm, it) }
        }
        Spacer(Modifier.padding(vertical = 15.dp))
        Text(text = character.lore, color = Color.White, modifier = Modifier.padding(horizontal = 20.dp))
    }
}

fun getIconsList(vm : IconViewModel): List<Icon> {
    return if (vm.getFavoriteMode()) {
        vm.getFavorites()
    }
    else {
        vm.icons.value
    }
}





