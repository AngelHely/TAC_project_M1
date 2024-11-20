package com.example.legends.viewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.legends.api.APIService
import com.example.legends.models.Character
import com.example.legends.models.CharacterRes
import com.example.legends.models.Icon
import com.example.legends.models.Image
import com.example.legends.useCase.CharacterUseCase
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel(){

    private val _character = mutableStateOf<Character>(
        Character(
            id = "",
            image = Image(image = ""),
            lore = "",
            title = ""
        )
    )



    fun getCharacter(champID : String?): Character {
        viewModelScope.launch {
            val characterUseCase = CharacterUseCase()
            try {
                _character.value = characterUseCase.getCharacterUseCase(champID).data[champID]!!
            } catch (e: Exception) {
                Log.d("CHARACTER", "error : ${e.message.toString()}")
            }
        }
        return _character.value
    }
}