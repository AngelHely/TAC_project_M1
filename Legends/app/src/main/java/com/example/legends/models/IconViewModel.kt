package com.example.legends.models

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.legends.api.APIService
import com.example.legends.api.Icon
import kotlinx.coroutines.launch

class IconViewModel : ViewModel(){

    private val _icons = mutableStateOf<List<Icon>>(listOf())

    val icons : State<List<Icon>> = _icons

    init {
        getIcons()
    }

    private fun getIcons() {
        viewModelScope.launch {
            val apiService = APIService.retrofitIconService
            try {
                _icons.value = apiService.getIcons()
            } catch (e: Exception) {
                    Log.d("ICON", "error : ${e.message.toString()}")
            }
        }
    }

}