package com.example.legends.models

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.legends.api.APIService
import com.example.legends.api.Icon
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
            val apiService = APIService.retrofitIconService
            try {
                val response = apiService.getIcons()

                _icons.value = response.data.values.toList()
                size = _icons.value.size
            } catch (e: Exception) {
                    Log.d("ICON", "error : ${e.message.toString()}")
            }
        }
    }

}