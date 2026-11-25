package com.example.ud5_ejerciciotarea.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ud5_ejerciciotarea.modelo.Tarea
import com.example.ud5_ejerciciotarea.modelo.TareaUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel: ViewModel() {
    // Atributos realcionados con el uiState
    private val _tareaUItate = MutableStateFlow(TareaUIState())
    val tareaUIState: StateFlow<TareaUIState> = _tareaUItate.asStateFlow()

    // Atributos relacionados con la UI
    var titulo by mutableStateOf("")
        private set

    var descripcion by mutableStateOf("")
        private set

    var modulo by mutableStateOf("")
        private set

    var urgente by mutableStateOf(false)
        private set

    fun actualizarTitulo(titulo_ui: String) {
        titulo = titulo_ui

        val tarea = _tareaUItate.value.tarea.copy()
        tarea.titulo = titulo

        _tareaUItate.update { estadoActual ->
            estadoActual.copy(
                tarea = tarea
            )
        }
    }

    fun actualizarDescripcion(desc_ui: String) {
        descripcion = desc_ui

        val tarea = _tareaUItate.value.tarea.copy()
        tarea.descripcion = descripcion

        _tareaUItate.update { estadoActual ->
            estadoActual.copy(
                tarea = tarea
            )
        }
    }

    fun actualizarModulo(modulo_ui: String) {
        modulo = modulo_ui

        val tarea = _tareaUItate.value.tarea.copy()
        tarea.modulo = modulo

        _tareaUItate.update { estadoActual ->
            estadoActual.copy(
                tarea = tarea
            )
        }
    }

    fun actualizarUrgente(urgente_ui: Boolean) {
        urgente = urgente_ui

        val tarea = _tareaUItate.value.tarea.copy()
        tarea.urgente = urgente

        _tareaUItate.update { estadoActual ->
            estadoActual.copy(
                tarea = tarea
            )
        }
    }

    fun insertarTarea() {
        _tareaUItate.update { estadoActual ->
            estadoActual.copy(
                tarea = Tarea(),
                listaTareas = estadoActual.listaTareas + estadoActual.tarea
            )
        }
    }
}