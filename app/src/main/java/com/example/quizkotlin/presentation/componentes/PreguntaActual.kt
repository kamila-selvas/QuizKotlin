package com.example.quizkotlin.presentation.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.quizkotlin.R
import com.example.quizkotlin.presentation.TriviaUiState

@Composable
fun PreguntaActual(
    uiState: TriviaUiState,
    onOpcionElegida: (Int) -> Unit,
    onResponder: () -> Unit
) {
    val pregunta = uiState.preguntaActual ?: return

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(
                    R.string.progreso_pregunta,
                    uiState.indiceActual + 1,
                    uiState.preguntas.size
                ),
                style = MaterialTheme.typography.labelLarge
            )
            PastillaPuntaje(puntaje = uiState.puntaje)
        }

        LinearProgressIndicator(
            progress = { uiState.progreso },
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = pregunta.enunciado,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold
        )

        pregunta.opciones.forEachIndexed { indice, opcion ->
            TarjetaOpcion(
                letra = 'A' + indice,
                texto = opcion,
                elegida = uiState.opcionElegida == indice,
                onClick = { onOpcionElegida(indice) }
            )
        }

        Button(
            onClick = onResponder,
            enabled = uiState.opcionElegida != null,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(if (uiState.esUltima) R.string.ver_resultado else R.string.siguiente))
        }
    }
}
