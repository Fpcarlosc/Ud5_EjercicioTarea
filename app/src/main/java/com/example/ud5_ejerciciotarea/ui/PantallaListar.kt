package com.example.ud5_ejerciciotarea.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ud5_ejerciciotarea.R
import com.example.ud5_ejerciciotarea.modelo.TareaUIState

@Composable
fun PantallaListar(
    uiState: TareaUIState,
    modifier: Modifier = Modifier
){
    LazyColumn(modifier = modifier) {
        items(uiState.listaTareas) { tarea ->
            ListItem(
                modifier = Modifier
                    .padding(10.dp)
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RectangleShape
                    ),
                leadingContent = {
                    Text(
                        text = tarea.modulo,
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                headlineContent = {
                    Text(
                        text = tarea.titulo,
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                supportingContent = {
                    Text(
                        text = tarea.descripcion,
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                trailingContent = {
                    if(tarea.urgente) {
                        Image(
                            painter = painterResource(R.drawable.urgente),
                            contentDescription = stringResource(R.string.urgente),
                            Modifier
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primaryContainer)
                                .padding(10.dp)
                        )
                    }
                }
            )

        }
    }
}