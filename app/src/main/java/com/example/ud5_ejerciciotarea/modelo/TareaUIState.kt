package com.example.ud5_ejerciciotarea.modelo

data class TareaUIState(
    var tarea: Tarea = Tarea(),
    var listaTareas: List<Tarea> = listOf()
)