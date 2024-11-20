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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.legends.api.models.Icon
import com.example.legends.MVVM.viewModels.CharacterViewModel
import com.example.legends.MVVM.viewModels.IconViewModel
import com.example.legends.navigation.NavigationMenu
import com.example.legends.ui.theme.DarkBackgroundColor
import com.example.legends.ui.theme.DarkCharacterCardColor
import com.example.legends.ui.theme.LegendsTheme

class CharactersMenuActivity : ComponentActivity() {

    private val iconViewModel : IconViewModel by viewModels()
    private val characterViewModel : CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModels = mutableStateOf(mapOf(
            "icon" to iconViewModel, "character" to characterViewModel
        ))
        setContent {
            LegendsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavigationMenu(modifier = Modifier.padding(innerPadding), context = this, vms = viewModels)
                }
            }
        }
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
    LazyVerticalGrid (columns = GridCells.Fixed(2),modifier = modifier.background(color = DarkBackgroundColor)) {
        Log.d("SIZE", "${vm.size}")
        items(vm.icons.value) {
                icon -> CharacterCard(icon, navController)
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
    val character = vm.getCharacter(id)
    Column(modifier = modifier.background(color = DarkCharacterCardColor).fillMaxSize()) {
        Row (modifier = Modifier.padding(20.dp), horizontalArrangement = Arrangement.Center){
            AsyncImage(
                model = "https://ddragon.leagueoflegends.com/cdn/13.16.1/img/champion/${character.image.image}",
                contentDescription = null,
                modifier = Modifier.size(125.dp)
            )
            Spacer(modifier = Modifier.padding(horizontal = 10.dp))
            Column {
                Text(text = character.id.toString(), color = Color.White, fontSize = 40.sp)
                Text(text = character.title, color = Color.White, fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.padding(horizontal = 10.dp))
        }
        Spacer(Modifier.padding(vertical = 15.dp))
        Text(text = character.lore, color = Color.White, modifier = Modifier.padding(horizontal = 20.dp))
    }
}
