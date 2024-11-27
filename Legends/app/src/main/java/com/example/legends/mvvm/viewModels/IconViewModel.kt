package com.example.legends.mvvm.viewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.legends.api.models.Icon
import com.example.legends.mvvm.useCase.IconUseCase
import kotlinx.coroutines.launch

class IconViewModel(private val useCase: IconUseCase) : ViewModel(){

    private val _icons = mutableStateOf<List<Icon>>(listOf())

    val icons : MutableState<List<Icon>> = _icons

    var size : Int = 0

    init {
        getIcons()
    }

    private fun getIcons() {
        viewModelScope.launch {
            try {
                val response = useCase.getIconUseCase()

                _icons.value = response.data.values.toList()
                size = _icons.value.size
            } catch (e: Exception) {
                    Log.d("ICON", "error : ${e.message.toString()}")
            }
        }
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