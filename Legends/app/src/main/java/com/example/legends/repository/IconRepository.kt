package com.example.legends.repository

import com.example.legends.api.APIService
import com.example.legends.models.IconsList

class IconRepository {


    suspend fun getIconRepository(): IconsList {
        val apiService = APIService.retrofitService
        return apiService.getIcons()
    }

}