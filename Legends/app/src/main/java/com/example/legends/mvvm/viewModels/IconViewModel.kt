package com.example.legends.mvvm.viewModels

import android.text.BoringLayout
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.legends.api.models.Icon
import com.example.legends.mvvm.useCase.IconUseCase
import kotlinx.coroutines.launch

class IconViewModel(private val useCase: IconUseCase) : ViewModel(){

    private val _icons = mutableStateOf<List<Icon>>(listOf())

    val icons : MutableState<List<Icon>> = _icons

    private var favorites = mutableListOf<Icon>()
    private var favoriteMode = mutableStateOf(false)

    private val isLoading: MutableState<Boolean> = mutableStateOf(false)

    init {
        getIcons()
        refresh()
    }

    private fun getIcons() {
        isLoading.value = true
        viewModelScope.launch {
            try {
                val response = useCase.getIconUseCase()

                _icons.value = response.data.values.toList()
            } catch (e: Exception) {
                    Log.d("ICON", "error : ${e.message.toString()}")
            }
            finally {
                isLoading.value = false
            }
        }
    }

    fun getFavorites(): MutableList<Icon> {
        return this.favorites
    }

    fun getFavoriteMode() : Boolean {
        return this.favoriteMode.value
    }

    suspend fun exists(id : String): Boolean {
        return useCase.exists(id)
    }

    private fun refresh() {
        viewModelScope.launch {
            favorites = useCase.getAllCharactersUseCase().toMutableList()
        }
    }

    fun toggleFavoriteMode() {
        this.favoriteMode.value = !this.favoriteMode.value
    }

}

class IconViewModelFactory(private val useCase: IconUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IconViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return IconViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}