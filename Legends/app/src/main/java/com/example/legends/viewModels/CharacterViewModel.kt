package com.example.legends.viewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.legends.api.APIService
import com.example.legends.models.Character
import com.example.legends.models.Icon
import com.example.legends.models.Image
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel(){

    private val _character = mutableStateOf<Character>(
        Character(
        id = "",
        image = Image(image = ""),
        lore = ""
        )
    )


    fun getCharacter(champID : String?): Character {
        viewModelScope.launch {
            val apiService = APIService.retrofitIconService
            try {
                _character.value = apiService.getCharacter(champID).data[champID]!!
            } catch (e: Exception) {
                Log.d("CHARACTER", "error : ${e.message.toString()}")
            }
        }
        return _character.value
    }
}