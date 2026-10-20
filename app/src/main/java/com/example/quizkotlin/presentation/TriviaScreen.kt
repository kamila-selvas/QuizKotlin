package com.example.quizkotlin.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quizkotlin.AplicacionQuiz
import com.example.quizkotlin.R
import com.example.quizkotlin.data.modelo.Pregunta
import com.example.quizkotlin.presentation.componentes.FormularioPregunta
import com.example.quizkotlin.presentation.componentes.PantallaResultado
import com.example.quizkotlin.presentation.componentes.PreguntaActual

@Composable
fun TriviaScreen(
    viewModel: TriviaViewModel = viewModel(
        factory = (LocalContext.current.applicationContext as AplicacionQuiz).contenedor.fabrica
    )
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    TriviaContenido(
        uiState = uiState,
        onOpcionElegida = { indice -> viewModel.elegirOpcion(indice) },
        onResponder = { viewModel.responder() },
        onReiniciar = { viewModel.reiniciar() },
        onAgregarClick = { viewModel.abrirFormulario() },
        onEnunciadoChange = { texto -> viewModel.cambiarEnunciado(texto) },
        onOpcionChange = { indice, texto -> viewModel.cambiarOpcion(indice, texto) },
        onCorrectaChange = { indice -> viewModel.cambiarCorrecta(indice) },
        onGuardar = { viewModel.guardarPregunta() },
        onCancelar = { viewModel.cerrarFormulario() },
        onMensajeMostrado = { viewModel.mensajeMostrado() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TriviaContenido(
    uiState: TriviaUiState,
    onOpcionElegida: (Int) -> Unit,
    onResponder: () -> Unit,
    onReiniciar: () -> Unit,
    onAgregarClick: () -> Unit,
    onEnunciadoChange: (String) -> Unit,
    onOpcionChange: (Int, String) -> Unit,
    onCorrectaChange: (Int) -> Unit,
    onGuardar: () -> Unit,
    onCancelar: () -> Unit,
    onMensajeMostrado: () -> Unit,
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val textoMensaje = uiState.mensaje?.let { stringResource(it) }

    LaunchedEffect(textoMensaje) {
        if (textoMensaje != null) {
            snackbarHostState.showSnackbar(textoMensaje)
            onMensajeMostrado()
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.titulo_app)) },
                actions = {
                    if (!uiState.agregando) {
                        TextButton(onClick = onAgregarClick) {
                            Text(stringResource(R.string.agregar_pregunta))
                        }
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            when {
                uiState.agregando -> FormularioPregunta(
                    formulario = uiState.formulario,
                    onEnunciadoChange = onEnunciadoChange,
                    onOpcionChange = onOpcionChange,
                    onCorrectaChange = onCorrectaChange,
                    onGuardar = onGuardar,
                    onCancelar = onCancelar
                )
                uiState.terminado -> PantallaResultado(
                    puntaje = uiState.puntaje,
                    total = uiState.preguntas.size,
                    errores = uiState.errores,
                    porcentaje = uiState.porcentaje,
                    onReiniciar = onReiniciar
                )
                else -> PreguntaActual(
                    uiState = uiState,
                    onOpcionElegida = onOpcionElegida,
                    onResponder = onResponder
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreguntaPreview() {
    TriviaContenido(
        uiState = TriviaUiState(
            preguntas = listOf(
                Pregunta(
                    id = 1,
                    enunciado = "¿Qué palabra clave declara una variable inmutable en Kotlin?",
                    opciones = listOf("var", "val", "const", "let"),
                    indiceCorrecto = 1
                )
            ),
            opcionElegida = 1
        ),
        onOpcionElegida = {},
        onResponder = {},
        onReiniciar = {},
        onAgregarClick = {},
        onEnunciadoChange = {},
        onOpcionChange = { _, _ -> },
        onCorrectaChange = {},
        onGuardar = {},
        onCancelar = {},
        onMensajeMostrado = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun ResultadoPreview() {
    TriviaContenido(
        uiState = TriviaUiState(puntaje = 7, terminado = true),
        onOpcionElegida = {},
        onResponder = {},
        onReiniciar = {},
        onAgregarClick = {},
        onEnunciadoChange = {},
        onOpcionChange = { _, _ -> },
        onCorrectaChange = {},
        onGuardar = {},
        onCancelar = {},
        onMensajeMostrado = {}
    )
}
