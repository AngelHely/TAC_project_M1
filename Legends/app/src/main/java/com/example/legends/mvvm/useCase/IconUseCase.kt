package com.example.legends.mvvm.useCase

import com.example.legends.api.models.IconsList
import com.example.legends.mvvm.repository.IconRepository

class IconUseCase(private val repository: IconRepository){

    suspend fun getIconUseCase(): IconsList {
        return repository.getIconRepository()
    }

}