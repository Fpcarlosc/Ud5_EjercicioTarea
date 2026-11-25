package com.example.ud5_ejerciciotarea.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ud5_ejerciciotarea.Datos.listaModulos
import com.example.ud5_ejerciciotarea.R
import com.example.ud5_ejerciciotarea.modelo.TareaUIState

@Composable
fun PantallaInsertar(
    uiState: TareaUIState,
    onValueChangeTitulo: (String) -> Unit,
    onValueChangeDescripcion: (String) -> Unit,
    onClickModulo: (String) -> Unit,
    onCheckedChangeUrgente: (Boolean) -> Unit,
    onClickInsertarTarea: () -> Unit,
    onClickListarTareas: () -> Unit,
    modifier: Modifier = Modifier
) {
    // La lista de módulos se encuentra en el fichero Datos.kt
    var eleccModulo by remember { mutableStateOf(listaModulos[0])}

    // Variable para poder establecer el foco en el elemento deseado cuando se pulsa el botón (TextField de título)
    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = stringResource(R.string.inserta_nueva_tarea),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 10.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            // El título a mostrar será el que está almacenado en el uIState
            value = uiState.tarea.titulo,
            label = { Text(text = stringResource(R.string.titulo)) },
            onValueChange = { onValueChangeTitulo(it) },
            singleLine = true,
            modifier = Modifier.focusRequester(focusRequester) // Establecemos el foco en este elemento
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            // La descripción a mostrar será el que está almacenado en el uIState
            value = uiState.tarea.descripcion,
            label = { Text(text = stringResource(R.string.descripcion)) },
            onValueChange = { onValueChangeDescripcion(it) },
            modifier = Modifier
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(R.string.modulo_asociado),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 10.dp)
        )

        Row(
            Modifier
                .selectableGroup()
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            listaModulos.forEach { modulo ->
                Row(
                    modifier = Modifier.selectable(
                        selected = (modulo == eleccModulo),
                        onClick = {
                            eleccModulo = modulo
                            onClickModulo(modulo)
                        }
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (modulo == eleccModulo),
                        onClick = {
                            eleccModulo = modulo
                            onClickModulo(modulo)
                        })
                    Text(text = modulo)
                }
            }

        }
        Spacer(modifier = Modifier.height(20.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(R.string.urgente),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(10.dp)
            )
            Switch(
                // El valor de urgente será el que está almacenado en el uIState
                checked = uiState.tarea.urgente,
                onCheckedChange = { onCheckedChangeUrgente(it) }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(horizontalArrangement = Arrangement.Center) {
            Button(
                onClick = {
                    onClickInsertarTarea()
                    focusRequester.requestFocus() // Una vez insertada la tarea asignamos el foco al TextField de título.
                },
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Text(
                    text = stringResource(R.string.insertar),
                    fontWeight = FontWeight.Bold
                )
            }

            Button(
                onClick = { onClickListarTareas() },
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Text(
                    text = stringResource(R.string.listar),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}