package com.example.tfg.Screens

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.AlertDialog
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material.Card
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import java.time.Duration
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tfg.navigation.AppScreens
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Perfil(navController: NavHostController, context: Context) {
    val empleado =  remember { Empleado() }
    val scaffoldState = rememberScrollState()
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val snackbarHostState = remember { SnackbarHostState() }
    val showEntradaDialog = remember { mutableStateOf(false) }
    val showSalidaDialog = remember { mutableStateOf(false) }
    val showUpdateDialog = remember { mutableStateOf(false) } // Nuevo estado para el diálogo de actualización

    // Recuperar la hora de entrada al inicio
    val entradaTime = empleado.getEntradaTime(context)
    if (entradaTime != null) {
        empleado.horaEntrada.value = entradaTime
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    Button(
                        onClick = { navController.navigate(AppScreens.MESAS.ruta) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                    ) {
                        Text(
                            text = "MESAS",
                            fontSize = 25.sp,
                        )
                    }

                    Button(
                        onClick = { navController.navigate(AppScreens.Despensa.ruta) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                    ) {
                        Text(
                            text = "DESPENSA",
                            fontSize = 25.sp,
                        )
                    }

                    // Nuevo botón para actualizar los datos del usuario
                    Button(
                        onClick = { showUpdateDialog.value = true },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                    ) {
                        Text(
                            text = "Actualizar datos del usuario",
                            fontSize = 25.sp,
                        )
                    }
                }
            }
        },
    )
    {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Color.Blue,
                        titleContentColor = Color.White,
                    ),
                    title = {
                        Text("PERFIL")
                    },
                    navigationIcon = { // Nuevo botón de navegación
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Volver",
                                tint = Color.White
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Menu",
                                tint = Color.White
                            )
                        }
                    }
                )

            },
            snackbarHost = {
                SnackbarHost(snackbarHostState)
            }
        ) { innerPadding ->
            Column {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(innerPadding),
                    shape = MaterialTheme.shapes.medium,
                    elevation = 8.dp
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Fecha: ${LocalDate.now()}",
                            fontSize = 20.sp,
                        )
                    }
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(innerPadding),
                    shape = MaterialTheme.shapes.medium,
                    elevation = 8.dp
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Hora de entrada: ${empleado.horaEntrada.value}",
                            fontSize = 20.sp,
                        )
                        Button(
                            onClick = { showEntradaDialog.value = true },
                            colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                        ) {
                            Text("Fichar entrada")
                        }

                        if (showEntradaDialog.value) {
                            AlertDialog(
                                onDismissRequest = { showEntradaDialog.value = false },
                                title = { Text("Confirmar fichar entrada") },
                                text = { Text("¿Estás seguro de que quieres fichar la entrada?") },
                                confirmButton = {
                                    Button(
                                        onClick = {
                                            empleado.ficharEntrada()
                                            empleado.saveEntradaTime(context, empleado.horaEntrada.value!!)
                                            showEntradaDialog.value = false
                                        }
                                    ) {
                                        Text("Sí")
                                    }
                                },
                                dismissButton = {
                                    Button(
                                        onClick = { showEntradaDialog.value = false }
                                    ) {
                                        Text("No")
                                    }
                                }
                            )
                        }
                    }
                }


                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(innerPadding),
                    elevation = 8.dp,
                    shape = MaterialTheme.shapes.medium,
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Hora de salida: ${empleado.horaSalida.value}",
                            fontSize = 20.sp,
                        )
                        Button(
                            onClick = { showSalidaDialog.value = true },
                            colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                        ) {
                            Text("Fichar salida")
                        }

                        if (showSalidaDialog.value) {
                            AlertDialog(
                                onDismissRequest = { showSalidaDialog.value = false },
                                title = { Text("Confirmar fichar salida") },
                                text = { Text("¿Estás seguro de que quieres fichar la salida?") },
                                confirmButton = {
                                    Button(
                                        onClick = {
                                            scope.launch { empleado.ficharSalida(snackbarHostState) }
                                            showSalidaDialog.value = false
                                        }
                                    ) {
                                        Text("Sí")
                                    }
                                },
                                dismissButton = {
                                    Button(
                                        onClick = { showSalidaDialog.value = false }
                                    ) {
                                        Text("No")
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    // Nuevo diálogo para actualizar los datos del usuario
    if (showUpdateDialog.value) {
        AlertDialog(
            onDismissRequest = { showUpdateDialog.value = false },
            title = { Text("Actualizar datos del usuario") },
            text = {
                // Aquí es donde puedes poner tu interfaz de usuario para actualizar los datos del usuario
            },
            confirmButton = {
                Button(
                    onClick = {
                        // Aquí es donde puedes manejar la actualización de los datos del usuario
                        showUpdateDialog.value = false
                    }
                ) {
                    Text("Actualizar")
                }
            },
            dismissButton = {
                Button(
                    onClick = { showUpdateDialog.value = false }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}

class Empleado {
    var horaEntrada = mutableStateOf<LocalTime?>(null)
    var horaSalida = mutableStateOf<LocalTime?>(null)
    var fechaEntrada = mutableStateOf<LocalDate?>(null)
    var fechaSalida = mutableStateOf<LocalDate?>(null)

    @RequiresApi(Build.VERSION_CODES.O)
    fun ficharEntrada() {
        val now = LocalTime.now(ZoneId.of("Europe/Madrid")) // Reemplaza "Europe/Madrid" con tu zona horaria
        val today = LocalDate.now()

        if (fechaEntrada.value != today) {
            horaEntrada.value = now
            fechaEntrada.value = today
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun ficharSalida(snackbarHostState: SnackbarHostState) {
        val now = LocalTime.now(ZoneId.of("Europe/Madrid")) // Reemplaza "Europe/Madrid" con tu zona horaria
        val today = LocalDate.now()

        if (horaEntrada.value != null && fechaSalida.value != today) {
            horaSalida.value = now
            fechaSalida.value = today

            val duracion = Duration.between(horaEntrada.value, horaSalida.value)
            val horas = duracion.toHours()
            val minutos = duracion.toMinutes() - horas * 60 // Calculate minutes part manually

            val mensaje = "Has trabajado $horas horas y $minutos minutos."
            snackbarHostState.showSnackbar(mensaje)

            horaEntrada.value = null
            horaSalida.value = null}
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveEntradaTime(context: Context, time: LocalTime) {
        val sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putLong("entradaTime", time.toSecondOfDay().toLong())
        editor.apply()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getEntradaTime(context: Context): LocalTime? {
        val sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
        val timeInSeconds = sharedPreferences.getLong("entradaTime", -1)
        return if (timeInSeconds != -1L) LocalTime.ofSecondOfDay(timeInSeconds) else null
    }
}