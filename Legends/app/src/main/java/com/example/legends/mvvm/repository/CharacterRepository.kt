package com.example.legends.mvvm.repository

import com.example.legends.api.APIService
import com.example.legends.api.models.CharacterRes
import com.example.legends.api.models.Icon
import com.example.legends.room.dao.CharacterDao
import com.example.legends.room.database.CharacterEntity

class  CharacterRepository(private val dao : CharacterDao) {

    private val apiService = APIService.retrofitService

    suspend fun getCharacterRepository(id : String?): CharacterRes {
        return apiService.getCharacter(id)
    }

    suspend fun getAllCharacters() = dao.getAllCharacters()

    suspend fun addCharacter(name : String) {
        val c = Icon(id = name)
        dao.addCharacter(c)
    }

    suspend fun removeCharacter(id : String) {
        dao.removeCharacter(id)
    }


}