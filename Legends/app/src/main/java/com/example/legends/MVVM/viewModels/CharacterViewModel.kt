package com.example.legends.MVVM.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.legends.api.models.Character
import com.example.legends.api.models.Image
import com.example.legends.MVVM.useCase.CharacterUseCase
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