package com.example.quizkotlin.data.modelo

const val CANTIDAD_OPCIONES = 4

data class Pregunta(
    val id: Int,
    val enunciado: String,
    val opciones: List<String>,
    val indiceCorrecto: Int
)
