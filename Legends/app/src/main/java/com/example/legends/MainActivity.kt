package com.example.legends

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.legends.navigation.NavigationMenu
import com.example.legends.ui.theme.LegendsTheme

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LegendsTheme {
                Scaffold { contentPadding ->
                    NavigationMenu(modifier = Modifier.padding(contentPadding), context = this) {
                       param1, param2 ->
                    }
                }
            }
        }
    }
}