package com.example.tfg.Screens.PantallasClientes.Reservas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tfg.navigation.AppScreens
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Reservas(navController: NavHostController) {
    val scrollState = rememberScrollState() // Inicializar scrollState
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
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
                        onClick = { /*navController.navigate(AppScreens.DejarResena.ruta) */ },
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
                        onClick = { navController.navigate(AppScreens.MenuCritica.ruta) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .width(300.dp)
                            .height(100.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                    ) {
                        Text(
                            text = "Dejar reseña",
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
                        Text("RESERVA")
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
                    .padding(innerPadding)
                    .fillMaxSize()
                //.background(Color.Black)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        //.background(Color.Black)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally // Alinea el contenido horizontalmente
                ) {
                    //Hacer reserva, eliminarla, y otro cuadrado para obtenerla básicamente un crud

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        horizontalArrangement = Arrangement.Center // Centra horizontalmente los elementos en el Row
                    ) {
                        Card(
                            modifier = Modifier
                                .height(150.dp)
                                .width(150.dp)
                        ) {
                            IconButton(
                                onClick = { navController.navigate("Reservar") },
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Add,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(125.dp),
                                    tint = (Color.Black)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.size(36.dp))


                        Card(
                            modifier = Modifier
                                .height(150.dp)
                                .width(150.dp)
                        ) {
                            IconButton(
                                onClick = { navController.navigate("CancelarReserva") },
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Close,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(125.dp),
                                    tint = (Color.Black)
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.size(10.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = { navController.navigate(AppScreens.Reservar.ruta) },
                            modifier = Modifier
                                .height(40.dp)
                                .width(150.dp)


                        ) {
                            Text(
                                text = "RESERVAR",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                style = androidx.compose.ui.text.TextStyle(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                        Spacer(modifier = Modifier.size(36.dp))

                        Button(
                            onClick = { navController.navigate("CancelarReserva") },
                            modifier = Modifier
                                .height(40.dp)
                                .width(150.dp)
                        ) {
                            Text(
                                text = "CANCELAR",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                style = androidx.compose.ui.text.TextStyle(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }
                    Spacer(modifier = Modifier.size(30.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        horizontalArrangement = Arrangement.Center // Centra horizontalmente los elementos en el Row
                    ) {
                        Card(
                            modifier = Modifier
                                .height(150.dp)
                                .width(150.dp)
                        ) {
                            IconButton(
                                onClick = { navController.navigate("ConsultarReserva") },
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(125.dp),
                                    tint = (Color.Black)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.size(36.dp))
                        Card(
                            modifier = Modifier
                                .height(150.dp)
                                .width(150.dp)
                        ) {
                            IconButton(
                                onClick = { navController.navigate("ModificarReserva") },
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Build,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(125.dp),
                                    tint = (Color.Black)
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = { navController.navigate("ConsultarReserva") },
                            modifier = Modifier
                                .height(40.dp)
                                .width(150.dp)
                        ) {
                            Text(
                                text = "VER",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                style = androidx.compose.ui.text.TextStyle(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }

                        Spacer(modifier = Modifier.size(36.dp))
                        Button(
                            onClick = { navController.navigate("ModificarReserva") },
                            modifier = Modifier
                                .height(40.dp)
                                .width(160.dp)
                        ) {
                            Text(
                                text = "ACTUALIZAR",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                style = androidx.compose.ui.text.TextStyle(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                        }
                    }
                }
            }
        }
    }
