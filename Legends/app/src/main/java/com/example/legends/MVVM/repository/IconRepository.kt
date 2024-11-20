package com.example.legends.MVVM.repository

import com.example.legends.api.APIService
import com.example.legends.api.models.IconsList

class IconRepository {

    private val apiService = APIService.retrofitService


    suspend fun getIconRepository(): IconsList {
        return apiService.getIcons()
    }

}