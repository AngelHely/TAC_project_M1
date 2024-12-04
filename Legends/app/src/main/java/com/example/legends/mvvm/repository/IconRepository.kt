package com.example.legends.mvvm.repository

import com.example.legends.api.APIService
import com.example.legends.api.models.IconsList
import com.example.legends.room.dao.CharacterDao

class IconRepository(private val dao: CharacterDao) {

    private val apiService = APIService.retrofitService


    suspend fun getIconRepository(): IconsList {
        return apiService.getIcons()
    }

    suspend fun exists(id : String): Boolean {
        return dao.exists(id)
    }

    suspend fun getAllCharacters() = dao.getAllCharacters()


}