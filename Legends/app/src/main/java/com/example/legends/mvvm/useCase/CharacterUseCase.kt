package com.example.legends.mvvm.useCase

import com.example.legends.api.models.CharacterRes
import com.example.legends.mvvm.repository.CharacterRepository

class CharacterUseCase(private val repository : CharacterRepository) {

    suspend fun getCharacterUseCase(id : String?): CharacterRes {
        return repository.getCharacterRepository(id)
    }

    suspend fun exists(id : String): Boolean {
        return repository.exists(id)
    }

    suspend fun addCharacterUseCase(name : String) {
        repository.addCharacter(name)
    }

    suspend fun removeCharacterUseCase(id : String) {
        repository.removeCharacter(id)
    }
}