package com.example.legends.useCase

import com.example.legends.api.APIService
import com.example.legends.models.CharacterRes
import com.example.legends.repository.CharacterRepository

class CharacterUseCase {

    private val characterRepository = CharacterRepository()

    suspend fun getCharacterUseCase(id : String?): CharacterRes {
        return characterRepository.getCharacterRepository(id)
    }
}