package com.example.quizkotlin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quizkotlin.data.RepositorioPreguntas

class FabricaViewModel(
    private val repositorio: RepositorioPreguntas
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TriviaViewModel::class.java)) {
            return TriviaViewModel(repositorio) as T
        }
        throw IllegalArgumentException("ViewModel desconocido")
    }
}
