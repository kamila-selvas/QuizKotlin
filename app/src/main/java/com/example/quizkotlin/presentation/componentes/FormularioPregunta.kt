package com.example.quizkotlin.presentation.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.quizkotlin.R
import com.example.quizkotlin.presentation.FormularioUi

@Composable
fun FormularioPregunta(
    formulario: FormularioUi,
    onEnunciadoChange: (String) -> Unit,
    onOpcionChange: (Int, String) -> Unit,
    onCorrectaChange: (Int) -> Unit,
    onGuardar: () -> Unit,
    onCancelar: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = stringResource(R.string.form_titulo),
            style = MaterialTheme.typography.titleLarge
        )

        OutlinedTextField(
            value = formulario.enunciado,
            onValueChange = onEnunciadoChange,
            label = { Text(stringResource(R.string.form_enunciado)) },
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = stringResource(R.string.form_indicacion),
            style = MaterialTheme.typography.labelLarge
        )

        formulario.opciones.forEachIndexed { indice, opcion ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = formulario.indiceCorrecto == indice,
                    onClick = { onCorrectaChange(indice) }
                )
                OutlinedTextField(
                    value = opcion,
                    onValueChange = { onOpcionChange(indice, it) },
                    label = { Text(stringResource(R.string.form_opcion, indice + 1)) },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Button(
            onClick = onGuardar,
            enabled = formulario.esValido,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.form_guardar))
        }

        OutlinedButton(
            onClick = onCancelar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.form_cancelar))
        }
    }
}
