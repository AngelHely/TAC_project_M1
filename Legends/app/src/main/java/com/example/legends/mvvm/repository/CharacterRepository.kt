package com.example.legends.mvvm.repository

import com.example.legends.api.APIService
import com.example.legends.api.models.Character
import com.example.legends.api.models.CharacterRes
import com.example.legends.api.models.Icon
import com.example.legends.room.dao.CharacterDao

class  CharacterRepository(private val dao : CharacterDao) {

    private val apiService = APIService.retrofitService

    suspend fun getCharacterRepository(id : String?): CharacterRes {
        return apiService.getCharacter(id)
    }

    suspend fun exists(id : String): Boolean {
        return dao.exists(id)
    }

    suspend fun addCharacter(character: Character) {
        val c = Icon(id = character.id, title = character.title)
        dao.addCharacter(c)
    }

    suspend fun removeCharacter(id : String) {
        dao.removeCharacter(id)
    }


}