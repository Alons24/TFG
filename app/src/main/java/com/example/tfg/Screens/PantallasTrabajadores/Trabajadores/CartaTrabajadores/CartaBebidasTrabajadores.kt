package com.example.tfg.Screens.PantallasTrabajadores.Trabajadores.CartaTrabajadores

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tfg.navigation.AppScreens
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartaBebidasTrabajadores(navController: NavHostController) {
    var bebida by remember { mutableStateOf("") }
    var mensajeConfirmacion by remember { mutableStateOf("") }

    val db = FirebaseFirestore.getInstance()
    val coleccion = "Mesa1"

    val scaffoldState = rememberScrollState()
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

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
                        onClick = { navController.navigate(AppScreens.Despensa.ruta) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                    ) {
                        Text(
                            text = "DESPENSA",
                            fontSize = 50.sp,
                        )
                    }

                    Button(
                        onClick = { /*navController.navigate(AppScreens.Despensa.ruta)*/ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                    ) {
                        Text(
                            text = "VER RESEÑAS",
                            fontSize = 40.sp,
                        )
                    }

                    Button(
                        onClick = { navController.navigate(AppScreens.ConsultarReservaTrabajadores.ruta) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                    ) {
                        Text(
                            text = "CONSULTAR RESERVAS",
                            fontSize = 30.sp,
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
                    title = { Text("CARTA BEBIDA") },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigate("Mesa1") }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Localized description"
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
                    // Icono Adicional
                    BottomNavigationItem(
                        selected = false,
                        onClick = { navController.navigate("Perfil") },
                        modifier = Modifier.weight(1f),
                        icon = {
                            Icon(
                                imageVector = Icons.Default.AccountCircle, contentDescription = "YourIcon", tint = Color.White)
                        },
                    )
                }
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(MaterialTheme.colorScheme.background.copy(alpha = 0.8f))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                OutlinedTextField(
                    value = bebida,
                    onValueChange = { bebida = it },
                    singleLine = true,
                    label = { Text("Escriba el tipo de bebida") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    textStyle = TextStyle(fontSize = 18.sp),
                )

                Spacer(modifier = Modifier.height(15.dp))

                Button(
                    onClick = {
                        db.collection(coleccion)
                            .add(hashMapOf("bebida" to bebida))
                            .addOnSuccessListener {
                                mensajeConfirmacion = "Bebida encargada correctamente :)"
                                bebida = ""
                            }
                            .addOnFailureListener {
                                mensajeConfirmacion = "No se ha podido encargar la bebida :("
                            }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4CAF50),
                        contentColor = Color.Yellow
                    ),
                    border = BorderStroke(1.dp, Color.Black)
                ) {
                    Text(text = "Encargar bebida")
                }

                Text(
                    text = mensajeConfirmacion,
                    style = TextStyle(color = if (mensajeConfirmacion.startsWith("bebida encargada")) Color.Green else Color.Red)
                )
            }
        }
    }
}
