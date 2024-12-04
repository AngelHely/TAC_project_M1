package com.example.legends.mvvm.viewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.legends.api.models.Character
import com.example.legends.api.models.Icon
import com.example.legends.api.models.Image
import com.example.legends.mvvm.useCase.CharacterUseCase
import kotlinx.coroutines.launch

class CharacterViewModel(private val useCase: CharacterUseCase) : ViewModel(){

    private val _character = mutableStateOf(
        Character(
            id = "",
            image = Image(image = ""),
            lore = "",
            title = ""
        )
    )

    private var isFavorite = mutableStateOf(false)



    fun getCharacter(champID : String?): Character {
        viewModelScope.launch {
            try {
                _character.value = useCase.getCharacterUseCase(champID).data[champID]!!
                isFavorite.value = useCase.exists(_character.value.id)
            } catch (e: Exception) {
                Log.d("CHARACTER", "error : ${e.message.toString()}")
            }
        }
        return _character.value
    }

    fun isFavorite(): Boolean {
        return this.isFavorite.value
    }

     suspend fun exists(id : String) {
         viewModelScope.launch {
             isFavorite.value = useCase.exists(id)
         }
    }

    fun addCharacter(character: Character) {
        viewModelScope.launch {
            useCase.addCharacterUseCase(character)
            toggleFavorite(character.id)
        }

    }

    fun removeCharacter(id : String) {
        viewModelScope.launch {
            useCase.removeCharacterUseCase(id)
            toggleFavorite(id)
        }
    }

    private suspend fun toggleFavorite(id: String) {
            this.isFavorite.value = useCase.exists(id)
        }

}


class CharacterViewModelFactory(private val useCase: CharacterUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CharacterViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
