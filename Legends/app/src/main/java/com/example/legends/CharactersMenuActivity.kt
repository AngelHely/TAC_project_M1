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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.legends.models.IconViewModel
import com.example.legends.navigation.NavigationMenu
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
                        _, m -> View(iconViewModel, m)
                    }
                }
            }
        }
    }

    @Composable
    fun View(vm : IconViewModel, modifier : Modifier) {
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
}
