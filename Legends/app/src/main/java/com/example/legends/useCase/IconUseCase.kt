package com.example.legends.useCase

import com.example.legends.api.APIService
import com.example.legends.models.IconsList

class IconUseCase {

    suspend fun getIconUseCase(): IconsList {
        val apiService = APIService.retrofitService
        return apiService.getIcons()
    }

}