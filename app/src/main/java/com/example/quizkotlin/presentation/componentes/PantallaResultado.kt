package com.example.quizkotlin.presentation.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.quizkotlin.R

@Composable
fun PantallaResultado(
    puntaje: Int,
    total: Int,
    onReiniciar: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(R.string.quiz_terminado),
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = stringResource(R.string.puntuacion, puntaje, total),
            style = MaterialTheme.typography.bodyLarge
        )
        Button(onClick = onReiniciar) {
            Text(stringResource(R.string.reiniciar))
        }
    }
}
