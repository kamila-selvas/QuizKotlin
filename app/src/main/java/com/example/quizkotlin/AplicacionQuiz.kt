package com.example.quizkotlin

import android.app.Application
import com.example.quizkotlin.di.Contenedor

class AplicacionQuiz : Application() {

    val contenedor: Contenedor by lazy {
        Contenedor(applicationContext)
    }
}
