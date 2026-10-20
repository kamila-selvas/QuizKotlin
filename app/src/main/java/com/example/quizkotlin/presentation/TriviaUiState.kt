package com.example.quizkotlin.presentation

import androidx.annotation.StringRes
import com.example.quizkotlin.data.modelo.CANTIDAD_OPCIONES
import com.example.quizkotlin.data.modelo.Pregunta

data class FormularioUi(
    val enunciado: String = "",
    val opciones: List<String> = List(CANTIDAD_OPCIONES) { "" },
    val indiceCorrecto: Int = 0
) {
    val esValido: Boolean
        get() = enunciado.isNotBlank() && opciones.all { it.isNotBlank() }
}

data class TriviaUiState(
    val preguntas: List<Pregunta> = emptyList(),
    val indiceActual: Int = 0,
    val opcionElegida: Int? = null,
    val puntaje: Int = 0,
    val terminado: Boolean = false,
    val agregando: Boolean = false,
    val formulario: FormularioUi = FormularioUi(),
    @StringRes val mensaje: Int? = null
) {
    val preguntaActual: Pregunta?
        get() = preguntas.getOrNull(indiceActual)

    val esUltima: Boolean
        get() = indiceActual == preguntas.lastIndex

    val errores: Int
        get() = preguntas.size - puntaje

    val porcentaje: Int
        get() = if (preguntas.isEmpty()) 0 else puntaje * 100 / preguntas.size

    val progreso: Float
        get() = if (preguntas.isEmpty()) 0f else (indiceActual + 1f) / preguntas.size
}
