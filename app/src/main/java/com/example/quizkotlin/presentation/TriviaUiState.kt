package com.example.quizkotlin.presentation

import androidx.annotation.StringRes
import com.example.quizkotlin.data.modelo.CANTIDAD_OPCIONES
import com.example.quizkotlin.data.modelo.Pregunta

/** Estado del formulario para agregar una pregunta. */
data class FormularioUi(
    val enunciado: String = "",
    val opciones: List<String> = List(CANTIDAD_OPCIONES) { "" },
    val indiceCorrecto: Int = 0
) {
    val esValido: Boolean
        get() = enunciado.isNotBlank() && opciones.all { it.isNotBlank() }
}

/** Todo lo que la pantalla necesita para dibujarse. Es inmutable: se cambia con copy(). */
data class TriviaUiState(
    val preguntas: List<Pregunta> = emptyList(),
    val indiceActual: Int = 0,
    val opcionElegida: Int? = null,       // null = todavia no elige
    val puntaje: Int = 0,
    val terminado: Boolean = false,
    val agregando: Boolean = false,       // true = se muestra el formulario
    val formulario: FormularioUi = FormularioUi(),
    @StringRes val mensaje: Int? = null   // aviso de una sola vez (ej. "Pregunta guardada")
) {
    val preguntaActual: Pregunta?
        get() = preguntas.getOrNull(indiceActual)

    val esUltima: Boolean
        get() = indiceActual == preguntas.lastIndex

    val progreso: Float
        get() = if (preguntas.isEmpty()) 0f else (indiceActual + 1f) / preguntas.size
}
