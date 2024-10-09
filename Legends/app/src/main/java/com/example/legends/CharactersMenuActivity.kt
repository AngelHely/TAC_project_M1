package com.example.legends

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.legends.models.IconViewModel
import com.example.legends.navigation.NavigationMenu
import com.example.legends.ui.theme.LegendsTheme

class CharactersMenuActivity : ComponentActivity() {

//    private val iconViewModel : IconViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LegendsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavigationMenu(modifier = Modifier.padding(innerPadding), context = this) {
//                        View(iconViewModel, modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }

//    @Composable
//    fun View(vm : IconViewModel, modifier : Modifier) {
//        LazyColumn(modifier = Modifier.fillMaxHeight()) {
//            items(iconViewModel.icons.value) { icon ->
//                Column {
//                    Row(
//                        modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(16.dp),
//                        horizontalArrangement = Arrangement.SpaceBetween
//                    )
//                    {
//                        AsyncImage(model= icon.image, null)
//                    }
//                }
//
//            }
//        }
//    }
}
