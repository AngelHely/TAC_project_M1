package com.example.legends

import androidx.compose.foundation.background
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import com.example.legends.api.models.Icon


fun filter(list : List<Icon>, text: String): List<Icon> {
    return list.filter { it.id.lowercase().startsWith(text.lowercase()) }
}

@Composable
fun EditTextField(
    text: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier
) {
        TextField(
            value = text,
            singleLine = true,
            modifier = modifier,
            onValueChange = onValueChanged,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Gray,
                unfocusedContainerColor = Color.Gray,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White  )
        )
    }

