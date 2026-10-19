package com.example.quizkotlin.data.modelo

const val CANTIDAD_OPCIONES = 4

data class Pregunta(
    val id: Int,
    val enunciado: String,
    val opciones: List<String>,   // siempre 4 opciones
    val indiceCorrecto: Int       // posicion (0 a 3) de la opcion correcta
)
