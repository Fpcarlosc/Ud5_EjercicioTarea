package com.example.ud5_ejerciciotarea.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ud5_ejerciciotarea.R
import com.example.ud5_ejerciciotarea.ui.theme.arrow_back

enum class Pantallas(@StringRes val titulo: Int) {
    InsertarTarea(titulo = R.string.insertar_tarea),
    ListarTareas(titulo = R.string.listar_tareas)
}

@Composable
fun Ud5EjercicioTarea(
    viewModel: AppViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){

    // Para controlar que en la primera pantalla no aparezca la flecha de atrás en el TopBar
    // controlamos si en la pila de retroceso hay alguna patnalla destrás de la actual.
    // Si la hay, mostramos la flecha.
    val pilaRetroceso by navController.currentBackStackEntryAsState()

    val pantallaActual = Pantallas.valueOf(
        pilaRetroceso?.destination?.route ?: Pantallas.InsertarTarea.name
    )

    Scaffold(
        topBar = {
            AppTopBar(
                pantallaActual = pantallaActual,
                puedeNavegarAtras = navController.previousBackStackEntry != null,
                onNavegarAtras = {navController.navigateUp()}
            )
        }
    ) { innerPadding ->

        val uiState by viewModel.tareaUIState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = Pantallas.InsertarTarea.name,
            modifier = Modifier.padding(innerPadding)
        ){
            // Grafo de las rutas
            composable(route = Pantallas.InsertarTarea.name) {
                PantallaInsertar(
                    uiState = uiState,
                    onValueChangeTitulo = { viewModel.actualizarTitulo(it) },
                    onValueChangeDescripcion = { viewModel.actualizarDescripcion(it) },
                    onClickModulo = { viewModel.actualizarModulo(it) },
                    onCheckedChangeUrgente = { viewModel.actualizarUrgente(it) },
                    onClickInsertarTarea = { viewModel.insertarTarea() },
                    onClickListarTareas = { navController.navigate(Pantallas.ListarTareas.name) },
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(route = Pantallas.ListarTareas.name) {
                PantallaListar(
                    uiState = uiState,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    pantallaActual: Pantallas,
    puedeNavegarAtras: Boolean,
    onNavegarAtras: () -> Unit,
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = { Text(text = stringResource(id = pantallaActual.titulo)) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        navigationIcon = {
            if(puedeNavegarAtras) {
                IconButton(onClick = onNavegarAtras) {
                    Icon(
                        imageVector = arrow_back,
                        contentDescription = stringResource(id = R.string.atras)
                    )
                }
            }
        },
        modifier = modifier
    )
}