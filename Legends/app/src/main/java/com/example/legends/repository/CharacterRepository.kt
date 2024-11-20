package com.example.legends.repository

import com.example.legends.api.APIService
import com.example.legends.models.CharacterRes
import com.example.legends.models.IconsList

class  CharacterRepository {

    suspend fun getCharacterRepository(id : String?): CharacterRes {

        val apiService = APIService.retrofitService
        return apiService.getCharacter(id)
    }


}