package com.example.quizkotlin

import android.app.Application
import com.example.quizkotlin.di.Contenedor

/** Se crea una sola vez cuando se abre la app; aquí vive el contenedor. */
class AplicacionQuiz : Application() {

    val contenedor: Contenedor by lazy {
        Contenedor(applicationContext)
    }
}
