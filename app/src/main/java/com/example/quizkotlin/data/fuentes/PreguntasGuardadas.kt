package com.example.quizkotlin.data.fuentes

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.quizkotlin.data.modelo.Pregunta
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.json.JSONArray
import org.json.JSONObject

// Crea (una sola vez) el archivo donde DataStore guarda los datos.
private val Context.almacen: DataStore<Preferences> by preferencesDataStore(name = "quiz_datos")

/** Preguntas que escribe el usuario: se guardan con DataStore y siguen ahi al cerrar la app. */
class PreguntasGuardadas(private val context: Context) {

    private val clavePreguntas = stringPreferencesKey("preguntas_usuario")

    // Flow: vuelve a emitir la lista cada vez que se guarda una pregunta nueva.
    val preguntas: Flow<List<Pregunta>> = context.almacen.data.map { preferencias ->
        preferencias[clavePreguntas]?.let { desdeJson(it) } ?: emptyList()
    }

    suspend fun guardar(pregunta: Pregunta) {
        context.almacen.edit { preferencias ->
            val actuales = preferencias[clavePreguntas]?.let { desdeJson(it) } ?: emptyList()
            preferencias[clavePreguntas] = aJson(actuales + pregunta)
        }
    }

    // DataStore guarda texto, asi que la lista se convierte a JSON y de regreso.
    private fun aJson(lista: List<Pregunta>): String {
        val arreglo = JSONArray()
        lista.forEach { pregunta ->
            arreglo.put(
                JSONObject()
                    .put("id", pregunta.id)
                    .put("enunciado", pregunta.enunciado)
                    .put("opciones", JSONArray(pregunta.opciones))
                    .put("indiceCorrecto", pregunta.indiceCorrecto)
            )
        }
        return arreglo.toString()
    }

    private fun desdeJson(json: String): List<Pregunta> {
        val arreglo = JSONArray(json)
        return List(arreglo.length()) { i ->
            val objeto = arreglo.getJSONObject(i)
            val opciones = objeto.getJSONArray("opciones")
            Pregunta(
                id = objeto.getInt("id"),
                enunciado = objeto.getString("enunciado"),
                opciones = List(opciones.length()) { opciones.getString(it) },
                indiceCorrecto = objeto.getInt("indiceCorrecto")
            )
        }
    }
}
