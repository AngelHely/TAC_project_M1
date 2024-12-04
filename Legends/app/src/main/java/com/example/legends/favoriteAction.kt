package com.example.legends

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.example.legends.mvvm.viewModels.CharacterViewModel

@Composable
fun FavoriteButton(vm : CharacterViewModel, name : String) {
    var isFavorite by remember { mutableStateOf(false)  }
    LaunchedEffect(Unit) {
        isFavorite = vm.exists(name)
        Log.d("FAV", vm.exists(name).toString())
    }
    IconButton(onClick = {
        action(vm, name, isFavorite)
    })
    {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "add Character",
            tint = if (isFavorite) Color.Yellow else Color.White
        )
    }
}

fun action(vm : CharacterViewModel, name : String, isFavorite : Boolean) {
    if (isFavorite) {
        vm.removeCharacter(name)
    }
    else {
        vm.addCharacter(name)
    }
}