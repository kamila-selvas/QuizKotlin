package com.example.quizkotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.quizkotlin.presentation.TriviaScreen
import com.example.quizkotlin.ui.theme.QuizKotlinTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizKotlinTheme {
                TriviaScreen()
            }
        }
    }
}
