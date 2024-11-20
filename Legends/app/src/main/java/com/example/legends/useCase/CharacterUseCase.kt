package com.example.legends.useCase

import com.example.legends.api.APIService
import com.example.legends.models.CharacterRes

class CharacterUseCase {

    suspend fun getCharacterUseCase(id : String?): CharacterRes {

        val apiService = APIService.retrofitService
        return apiService.getCharacter(id)
    }
}