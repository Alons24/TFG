package com.example.tfg.Screens.PantallasClientes

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Slider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tfg.R
import com.example.tfg.Retrofit.DataClases.Critica
import com.example.tfg.Retrofit.SessionManager
import com.example.tfg.Retrofit.ViewModels.CriticaViewModel
import com.example.tfg.navigation.AppScreens
import kotlinx.coroutines.launch
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrearCritica(navController: NavHostController, viewModel: CriticaViewModel) {
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

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
                        onClick = { navController.navigate(AppScreens.CrearCritica.ruta) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                    ) {
                        Text(
                            text = "Escaner QR",
                            fontSize = 20.sp,
                        )
                    }

                    Button(
                        onClick = { navController.navigate(AppScreens.Reservas.ruta) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                    ) {
                        Text(
                            text = "Menú Platos",
                            fontSize = 20.sp,
                        )
                    }

                    Button(
                        onClick = { navController.navigate(AppScreens.CrearCritica.ruta) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                    ) {
                        Text(
                            text = "Hacer Reserva",
                            fontSize = 20.sp,
                        )
                    }
                }
            }
        },
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Color.Blue,
                        titleContentColor = Color.White,
                    ),
                    title = {
                        Text("Dejar Reseña")
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack( )}) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
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
            bottomBar = {
                BottomAppBar(
                    containerColor = Color.Blue,
                    contentColor = MaterialTheme.colorScheme.primary,
                ) {
                    // Aquí puedes agregar íconos o elementos de navegación adicionales si es necesario
                }
            },
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .verticalScroll(scrollState),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var mensaje by remember { mutableStateOf("") }
                    OutlinedTextField(
                        value = mensaje,
                        onValueChange = { mensaje = it },
                        singleLine = true,
                        label = {
                            Text("Introduzca la reseña que desee")
                        },
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )

                    var valoracion by remember { mutableStateOf(1f) }
                    Slider(
                        value = valoracion,
                        onValueChange = { valoracion = it },
                        valueRange = 1f..5f,
                        steps = 4,
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )

                    val nombreUsuario = SessionManager.getUserName(context)
                    val db = FirebaseFirestore.getInstance()
                    val coleccion = db.collection("Critica")
                    var mensajeConfirmacion by remember { mutableStateOf("") }
                    var showToast by remember { mutableStateOf(false) }
                    val idCritica = coleccion.document().id

                    Button(
                        onClick = {
                            if (mensaje.isNotEmpty() && valoracion != 0f) {
                                val critica = Critica(idCritica, mensaje, valoracion, nombreUsuario)
                                viewModel.crearCritica(critica)
                                showDialog = true
                            } else {
                                mensajeConfirmacion = "Por favor, rellene todos los campos"
                                showToast = true
                            }
                        },
                        modifier = Modifier.fillMaxWidth(0.8f),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                    ) {
                        Text(
                            text = "Enviar Reseña",
                            fontSize = 20.sp,
                        )
                    }

                    if (showDialog) {
                        AlertDialog(
                            onDismissRequest = { showDialog = false },
                            title = { Text("Confirmación") },
                            text = { Text("¿Estás seguro de que quieres enviar la reseña?") },
                            confirmButton = {
                                Button(onClick = {
                                    showDialog = false
                                    navController.navigate("MenuClientes")
                                }) {
                                    Text("Confirmar")
                                }
                            },
                            dismissButton = {
                                Button(onClick = { showDialog = false }) {
                                    Text("Cancelar")
                                }
                            }
                        )
                    }

                    if (showToast) {
                        val context = LocalContext.current
                        LaunchedEffect(key1 = showToast) {
                            Toast.makeText(context, mensajeConfirmacion, Toast.LENGTH_LONG).show()
                            showToast = false
                        }
                    }

                    Box(modifier = Modifier.weight(1f))

                    Image(
                        painter = painterResource(R.drawable.cervecer_a_montemayorjpg),
                        contentDescription = "Título de la empresa",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                }
            }
        }
    }
}
