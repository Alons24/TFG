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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.AlertDialog
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Slider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
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
import com.example.tfg.Retrofit.ViewModels.CriticaViewModel
import com.example.tfg.navigation.AppScreens
import kotlinx.coroutines.launch
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DejarResena(navController: NavHostController, viewModel: CriticaViewModel) {
    val scrollState = rememberScrollState() // Inicializar scrollState
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var showDialog by remember { mutableStateOf(false) }

    /*Inicio del cajón lateral*/
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
                    //BOTÓN PARA BOLVER AL MENÚ DE INICIO
                    // Otros elementos del menú lateral
                    Button(
                        onClick = { /*navController.navigate(AppScreens.DejarResena.ruta) */},
                        modifier = Modifier
                            .fillMaxWidth()
                            .width(300.dp)
                            .height(100.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                    ) {
                        Text(
                            text = "Escaner qr",
                            fontSize = 40.sp,
                        )
                    }


                    Button(
                        onClick = { navController.navigate(AppScreens.Reservas.ruta) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .width(300.dp)
                            .height(100.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                    ) {
                        Text(
                            text = "Menu platos",
                            fontSize = 40.sp,
                        )
                    }


                    Button(
                        onClick = { navController.navigate(AppScreens.DejarResena.ruta) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .width(300.dp)
                            .height(100.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                    ) {
                        Text(
                            text = "Hacer reserva",
                            fontSize = 40.sp,
                        )
                    }


                    //FIN DE LOS BOTONES DEL MENÚ LATERAL
                }
            }
        },
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Color.Blue, // Cambia el color de fondo
                        titleContentColor = Color.White, // Cambia el color del título
                    ),
                    title = {
                        Text("DEJAR RESEÑA")
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigate("MenuClientes") }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Localized description",
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
                    BottomNavigationItem(
                        selected = false,
                        onClick = { /* Código para la acción del segundo ícono */ },
                        modifier = Modifier.weight(1f),
                        icon = {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "YourIcon",
                                tint = Color.White
                            )
                        },
                    )
                }
            },
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = androidx.compose.ui.Alignment.TopCenter // Cambiar a la parte superior
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally // Centrar horizontalmente
                ) {
                    var mensaje by remember { mutableStateOf("") }
                    OutlinedTextField(
                        value = mensaje,
                        onValueChange = { mensaje = it },
                        singleLine = true,
                        label = {
                            Text("Introduzca la reseña que desee")
                        },
                        modifier = Modifier.fillMaxWidth(0.8f) // Ajustar el ancho del TextField
                    )

                    var valoracion by remember { mutableStateOf(1f) }
                    Slider(
                        value = valoracion,
                        onValueChange = { valoracion = it },
                        valueRange = 1f..5f,
                        steps = 4,
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )

                    val db = FirebaseFirestore.getInstance()
                    val coleccion = db.collection("Critica")
                    var showDialog by remember { mutableStateOf(false) }
                    var mensajeConfirmacion by remember { mutableStateOf("") }
                    var showToast by remember { mutableStateOf(false) }
                    val idCritica = coleccion.document().id

                    Button(
                        onClick = {
                            if(mensaje.isNotEmpty() && valoracion != 0f){
                                val critica = Critica(idCritica, mensaje, valoracion)
                                viewModel.crearCritica(critica)
                                showDialog = true
                            }else{
                                mensajeConfirmacion = "Por favor, rellene todos los campos"
                            }
                        },
                        // ...
                    ) {
                        Text(
                            text = "ENVIAR RESEÑA",
                            fontSize = 25.sp,
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
                            Toast.makeText(context, "ID de su reseña: $idCritica", Toast.LENGTH_LONG).show()
                            showToast = false
                        }
                    }

                    // Espaciado para que la imagen se mueva hacia abajo
                    Box(modifier = Modifier.weight(1f))

                    Image(
                        painter = painterResource(R.drawable.cervecer_a_montemayorjpg),
                        contentDescription = "TÍTULO DE LA EMPRESA",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                }
            }
        }
    }
}
