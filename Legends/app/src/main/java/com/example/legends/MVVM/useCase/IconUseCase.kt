package com.example.legends.MVVM.useCase

import com.example.legends.api.models.IconsList
import com.example.legends.MVVM.repository.IconRepository

class IconUseCase {

    private val iconRepository = IconRepository()

    suspend fun getIconUseCase(): IconsList {
        return iconRepository.getIconRepository()
    }

}