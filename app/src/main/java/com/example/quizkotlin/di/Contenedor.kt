package com.example.quizkotlin.di

import android.content.Context
import com.example.quizkotlin.data.RepositorioPreguntas
import com.example.quizkotlin.data.fuentes.PreguntasBase
import com.example.quizkotlin.data.fuentes.PreguntasGuardadas
import com.example.quizkotlin.presentation.FabricaViewModel

/** Arma las piezas de la app una sola vez: fuentes de datos -> repositorio -> fábrica. */
class Contenedor(private val context: Context) {

    private val base: PreguntasBase by lazy {
        PreguntasBase()
    }

    private val guardadas: PreguntasGuardadas by lazy {
        PreguntasGuardadas(context)
    }

    private val repositorio: RepositorioPreguntas by lazy {
        RepositorioPreguntas(base, guardadas)
    }

    val fabrica: FabricaViewModel by lazy {
        FabricaViewModel(repositorio)
    }
}
