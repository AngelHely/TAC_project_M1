package com.example.legends.useCase

import com.example.legends.api.APIService
import com.example.legends.models.IconsList
import com.example.legends.repository.IconRepository

class IconUseCase {

    private val iconRepository = IconRepository()

    suspend fun getIconUseCase(): IconsList {
        return iconRepository.getIconRepository()
    }

}