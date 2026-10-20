package com.example.quizkotlin.data

import com.example.quizkotlin.data.fuentes.PreguntasBase
import com.example.quizkotlin.data.fuentes.PreguntasGuardadas
import com.example.quizkotlin.data.modelo.Pregunta
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepositorioPreguntas(
    private val base: PreguntasBase,
    private val guardadas: PreguntasGuardadas
) {

    fun obtenerPreguntas(): Flow<List<Pregunta>> {
        return guardadas.preguntas.map { delUsuario -> base.obtener() + delUsuario }
    }

    suspend fun agregar(pregunta: Pregunta) {
        guardadas.guardar(pregunta)
    }
}
