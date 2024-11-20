package com.example.legends.MVVM.useCase

import com.example.legends.api.models.CharacterRes
import com.example.legends.MVVM.repository.CharacterRepository

class CharacterUseCase {

    private val characterRepository = CharacterRepository()

    suspend fun getCharacterUseCase(id : String?): CharacterRes {
        return characterRepository.getCharacterRepository(id)
    }
}