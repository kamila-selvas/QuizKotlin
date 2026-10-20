package com.example.quizkotlin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizkotlin.R
import com.example.quizkotlin.data.RepositorioPreguntas
import com.example.quizkotlin.data.modelo.Pregunta
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Guarda el estado de la pantalla y contiene las reglas del quiz.
 * Los Composables no deciden nada: solo avisan lo que hizo el usuario.
 */
class TriviaViewModel(
    private val repositorio: RepositorioPreguntas
) : ViewModel() {

    // _uiState se puede modificar (privado); uiState es solo lectura (publico).
    private val _uiState = MutableStateFlow(TriviaUiState())
    val uiState: StateFlow<TriviaUiState> = _uiState.asStateFlow()

    init {
        cargarPreguntas()
    }

    // Escucha el Flow del repositorio: si se guarda una pregunta nueva, el estado se actualiza solo.
    private fun cargarPreguntas() {
        viewModelScope.launch {
            repositorio.obtenerPreguntas().collect { preguntas ->
                _uiState.update { it.copy(preguntas = preguntas) }
            }
        }
    }

    // ---------- Quiz ----------

    fun elegirOpcion(indice: Int) {
        _uiState.update { it.copy(opcionElegida = indice) }
    }

    fun responder() {
        val estado = _uiState.value
        val pregunta = estado.preguntaActual ?: return
        val elegida = estado.opcionElegida ?: return

        val acerto = elegida == pregunta.indiceCorrecto
        val nuevoPuntaje = if (acerto) estado.puntaje + 1 else estado.puntaje
        val siguiente = estado.indiceActual + 1
        val terminado = siguiente >= estado.preguntas.size

        _uiState.update {
            it.copy(
                puntaje = nuevoPuntaje,
                indiceActual = if (terminado) estado.indiceActual else siguiente,
                opcionElegida = null,
                terminado = terminado
            )
        }
    }

    fun reiniciar() {
        _uiState.update { TriviaUiState(preguntas = it.preguntas) }
    }

    // ---------- Formulario para agregar pregunta ----------

    fun abrirFormulario() {
        _uiState.update { it.copy(agregando = true, formulario = FormularioUi()) }
    }

    fun cerrarFormulario() {
        _uiState.update { it.copy(agregando = false) }
    }

    fun cambiarEnunciado(texto: String) {
        _uiState.update { it.copy(formulario = it.formulario.copy(enunciado = texto)) }
    }

    fun cambiarOpcion(indice: Int, texto: String) {
        _uiState.update { estado ->
            val nuevas = estado.formulario.opciones.toMutableList().also { it[indice] = texto }
            estado.copy(formulario = estado.formulario.copy(opciones = nuevas))
        }
    }

    fun cambiarCorrecta(indice: Int) {
        _uiState.update { it.copy(formulario = it.formulario.copy(indiceCorrecto = indice)) }
    }

    fun guardarPregunta() {
        val estado = _uiState.value
        if (!estado.formulario.esValido) return

        val nueva = Pregunta(
            id = (estado.preguntas.maxOfOrNull { it.id } ?: 0) + 1,
            enunciado = estado.formulario.enunciado.trim(),
            opciones = estado.formulario.opciones.map { it.trim() },
            indiceCorrecto = estado.formulario.indiceCorrecto
        )
        viewModelScope.launch {
            repositorio.agregar(nueva)
            // Reinicia el quiz y deja un aviso para que la pantalla lo muestre una sola vez.
            _uiState.update {
                TriviaUiState(preguntas = it.preguntas, mensaje = R.string.pregunta_guardada)
            }
        }
    }

    // La pantalla avisa que ya mostró el mensaje, para que no se repita.
    fun mensajeMostrado() {
        _uiState.update { it.copy(mensaje = null) }
    }
}
