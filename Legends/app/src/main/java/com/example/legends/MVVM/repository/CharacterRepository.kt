package com.example.legends.MVVM.repository

import com.example.legends.api.APIService
import com.example.legends.api.models.CharacterRes
import com.example.legends.room.dao.CharacterDao

class  CharacterRepository {

    private val apiService = APIService.retrofitService

    suspend fun getCharacterRepository(id : String?): CharacterRes {
        return apiService.getCharacter(id)
    }

    fun getAllCharacters() =


}