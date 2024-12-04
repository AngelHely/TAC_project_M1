package com.example.legends.mvvm.viewModels

import android.util.Log
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
            try {
                _character.value = useCase.getCharacterUseCase(champID).data[champID]!!
            } catch (e: Exception) {
                Log.d("CHARACTER", "error : ${e.message.toString()}")
            }
        }
        return _character.value
    }

    suspend fun exists(id : String): Boolean {
        Log.d("FAV", useCase.exists(id).toString())
        return useCase.exists(id)
    }

    fun addCharacter(name : String) {
        viewModelScope.launch {
            useCase.addCharacterUseCase(name)
        }
    }

    fun removeCharacter(id : String) {
        viewModelScope.launch {
            useCase.removeCharacterUseCase(id)
        }
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
