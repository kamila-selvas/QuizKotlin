package com.example.quizkotlin.presentation.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.quizkotlin.R
import com.example.quizkotlin.ui.theme.ColorAcierto
import com.example.quizkotlin.ui.theme.ColorError

@Composable
fun PantallaResultado(
    puntaje: Int,
    total: Int,
    errores: Int,
    porcentaje: Int,
    onReiniciar: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = stringResource(R.string.quiz_terminado),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )

        Box(modifier = Modifier.size(170.dp), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                progress = { if (total == 0) 0f else puntaje.toFloat() / total },
                modifier = Modifier.fillMaxSize(),
                strokeWidth = 14.dp,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(R.string.puntaje_fraccion, puntaje, total),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(R.string.porcentaje_aciertos, porcentaje),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TarjetaDato(
                valor = puntaje.toString(),
                etiqueta = stringResource(R.string.aciertos),
                colorValor = ColorAcierto,
                modifier = Modifier.weight(1f)
            )
            TarjetaDato(
                valor = errores.toString(),
                etiqueta = stringResource(R.string.errores),
                colorValor = ColorError,
                modifier = Modifier.weight(1f)
            )
        }

        Button(onClick = onReiniciar, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(R.string.reiniciar))
        }
    }
}
