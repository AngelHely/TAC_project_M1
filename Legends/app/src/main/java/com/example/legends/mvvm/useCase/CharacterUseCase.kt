package com.example.legends.mvvm.useCase

import com.example.legends.api.models.CharacterRes
import com.example.legends.mvvm.repository.CharacterRepository

class CharacterUseCase(private val repository : CharacterRepository) {

    suspend fun getCharacterUseCase(id : String?): CharacterRes {
        return repository.getCharacterRepository(id)
    }
}