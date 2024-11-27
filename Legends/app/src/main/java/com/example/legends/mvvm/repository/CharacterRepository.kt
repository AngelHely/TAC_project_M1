package com.example.legends.mvvm.repository

import com.example.legends.api.APIService
import com.example.legends.api.models.CharacterRes
import com.example.legends.room.dao.CharacterDao

class  CharacterRepository(private val dao : CharacterDao) {

    private val apiService = APIService.retrofitService

    suspend fun getCharacterRepository(id : String?): CharacterRes {
        return apiService.getCharacter(id)
    }

//    fun getAllCharacters() =


}