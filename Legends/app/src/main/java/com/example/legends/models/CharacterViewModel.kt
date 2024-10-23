package com.example.legends.models

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.legends.api.APIService
import com.example.legends.api.Character
import com.example.legends.api.Icon
import com.example.legends.api.Image
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel(){

    private val _characters = mutableStateOf<Character>(Character(
        id = "",
        image = Icon("", Image("")),
        lore = ""))

//    val characters : MutableState<Character> = _characters


    fun getCharacters(champID : String?): Character {
        viewModelScope.launch {
            val apiService = APIService.retrofitIconService
            try {
                _characters.value = apiService.getCharacters(champID)
            } catch (e: Exception) {
                Log.d("CHARACTER", "error : ${e.message.toString()}")
            }
        }
        return _characters.value
    }
}