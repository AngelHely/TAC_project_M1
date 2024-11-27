package com.example.legends

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.legends.mvvm.viewModels.CharacterViewModel

@Composable
fun FavoriteButton(vm : CharacterViewModel, name : String) {
    val isFavorite =vm.getFavorites().contains(name)
    IconButton(onClick = {
        Action(vm, name, isFavorite)
    })
    {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "add Character",
            tint = if (isFavorite) Color.Yellow else Color.Transparent
        )
    }
}

fun Action(vm : CharacterViewModel, name : String, isFavorite : Boolean) {
    if (isFavorite) {
        vm.removeCharacter(name)
    }
    else {
        vm.addCharacter(name)
    }
}