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
import com.example.legends.api.models.Character
import com.example.legends.mvvm.viewModels.CharacterViewModel

@Composable
fun FavoriteButton(vm : CharacterViewModel, character : Character) {
    val name = character.id
    LaunchedEffect(Unit) {
        vm.exists(name)
    }
    IconButton(onClick = {
        action(vm, character)
    })
    {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "add Character",
            tint = if (vm.isFavorite()) Color.Yellow else Color.White
        )
    }
}

fun action(vm : CharacterViewModel, character : Character) {
    if (vm.isFavorite()) {
        vm.removeCharacter(character.id)
    }
    else {
        vm.addCharacter(character)
    }
}