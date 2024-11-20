package com.example.legends.MVVM.viewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.legends.api.models.Icon
import com.example.legends.MVVM.useCase.IconUseCase
import kotlinx.coroutines.launch

class IconViewModel : ViewModel(){

    private val _icons = mutableStateOf<List<Icon>>(listOf())

    val icons : MutableState<List<Icon>> = _icons

    var size : Int = 0

    init {
        getIcons()
    }

    private fun getIcons() {
        viewModelScope.launch {
            val iconsUseCase = IconUseCase()
            try {
                val response = iconsUseCase.getIconUseCase()

                _icons.value = response.data.values.toList()
                size = _icons.value.size
            } catch (e: Exception) {
                    Log.d("ICON", "error : ${e.message.toString()}")
            }
        }
    }

}