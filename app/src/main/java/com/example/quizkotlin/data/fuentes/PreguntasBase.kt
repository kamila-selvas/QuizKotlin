package com.example.quizkotlin.data.fuentes

import com.example.quizkotlin.data.modelo.Pregunta

/** Preguntas base: vienen incluidas en la app, guardadas en memoria. */
class PreguntasBase {

    private val lista = listOf(
        Pregunta(
            id = 1,
            enunciado = "¿Qué palabra clave declara una variable inmutable en Kotlin?",
            opciones = listOf("var", "val", "const", "let"),
            indiceCorrecto = 1
        ),
        Pregunta(
            id = 2,
            enunciado = "¿Cómo se declara un tipo que puede almacenar null?",
            opciones = listOf("String", "Nullable<String>", "String?", "String!"),
            indiceCorrecto = 2
        ),
        Pregunta(
            id = 3,
            enunciado = "¿Qué palabra clave se usa para declarar una función en Kotlin?",
            opciones = listOf("func", "function", "def", "fun"),
            indiceCorrecto = 3
        ),
        Pregunta(
            id = 4,
            enunciado = "¿Qué genera automáticamente una data class?",
            opciones = listOf("Solo un constructor vacío", "equals, hashCode, toString y copy", "Una interfaz", "Un hilo en segundo plano"),
            indiceCorrecto = 1
        ),
        Pregunta(
            id = 5,
            enunciado = "¿Qué anotación marca una función que dibuja interfaz en Compose?",
            opciones = listOf("@Composable", "@View", "@Compose", "@Screen"),
            indiceCorrecto = 0
        ),
        Pregunta(
            id = 6,
            enunciado = "¿Qué función de Compose conserva un valor entre recomposiciones?",
            opciones = listOf("val", "lazy", "remember", "store"),
            indiceCorrecto = 2
        ),
        Pregunta(
            id = 7,
            enunciado = "¿Qué es la recomposición?",
            opciones = listOf("Compilar de nuevo la app", "Reiniciar la Activity", "Borrar la pantalla y empezar de cero", "Volver a ejecutar los Composables cuyo estado cambió"),
            indiceCorrecto = 3
        ),
        Pregunta(
            id = 8,
            enunciado = "¿Qué responsabilidad tiene el ViewModel en MVVM?",
            opciones = listOf("Dibujar la interfaz", "Mantener el estado y la lógica de la pantalla", "Definir los colores del tema", "Compilar el proyecto"),
            indiceCorrecto = 1
        ),
        Pregunta(
            id = 9,
            enunciado = "¿Para qué sirve StateFlow?",
            opciones = listOf("Para guardar datos en disco", "Para crear animaciones", "Para exponer un estado observable que la interfaz recolecta", "Para navegar entre pantallas"),
            indiceCorrecto = 2
        ),
        Pregunta(
            id = 10,
            enunciado = "¿Qué significa state hoisting?",
            opciones = listOf("Crear el estado dentro de cada Composable", "Mover el estado fuera del Composable y pasarlo con valor y lambdas", "Guardar el estado en la nube", "Eliminar el estado de la app"),
            indiceCorrecto = 1
        )
    )

    fun obtener(): List<Pregunta> = lista
}
