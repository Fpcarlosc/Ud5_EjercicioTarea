package com.example.ud5_ejerciciotarea.modelo

data class Tarea(
    var titulo: String = "",
    var descripcion: String = "",
    var modulo: String = "",
    var urgente: Boolean = false
)