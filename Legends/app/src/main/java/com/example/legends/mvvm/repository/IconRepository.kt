package com.example.legends.mvvm.repository

import com.example.legends.api.APIService
import com.example.legends.api.models.IconsList

class IconRepository {

    private val apiService = APIService.retrofitService


    suspend fun getIconRepository(): IconsList {
        return apiService.getIcons()
    }

}