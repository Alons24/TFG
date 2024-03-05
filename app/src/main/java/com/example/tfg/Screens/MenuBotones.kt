package com.example.tfg.Screens

import com.example.tfg.navigation.AppScreens
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuBotones(navController: NavHostController) {

    val scaffoldState = rememberScrollState()
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            // Contenido del cajón de navegación
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Color.Gray) // Puedes cambiar el color o usar otra imagen como fondo
                    .padding(16.dp)
                    .width(200.dp) // Ancho del cajón lateral
                    .offset(x = if (drawerState.isOpen) 0.dp else (-200).dp) // Desplazar hacia la izquierda cuando está cerrado
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Gray) // Puedes cambiar el color o usar otra imagen como fondo
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Opciones de navegación

                    //Icono del perfil
                    IconButton(onClick = { navController.navigate("MenuBotones") }) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile",
                            modifier = Modifier.size(24.dp)
                        )
                        Text("Perfil")
                    }

                    //Icono Configuración
                    IconButton(onClick = { navController.navigate("MenuBotones") }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            modifier = Modifier.size(24.dp)
                        )
                        Text("Configuración")
                    }

                    IconButton(onClick = { "MenuBotones" }) {
                        // Opción para cerrar sesión u otra acción
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = "Logout",
                            modifier = Modifier.size(24.dp)
                        )
                        Text("Cerrar sesión")
                    }
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
                        Text("MENU")
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigate("Acceso") }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Localized description",
                                tint=Color.White
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Menu",
                                tint=Color.White
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
                    // Icono
                    BottomNavigationItem(
                        selected = false,
                        onClick = {/*QUE HAGA ALGOOOOOOOOOOOOOOOO*/},
                        modifier = Modifier.weight(1f),
                        icon = {
                            Icon(imageVector = Icons.Default.DateRange, contentDescription = "Search", tint = Color.White)
                        },
                    )

                    // Icono Adicional
                    BottomNavigationItem(
                        selected = false,
                        onClick = {
                            /* Código para la acción del segundo ícono */
                        },
                        modifier = Modifier.weight(1f),
                        icon = {
                            Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "YourIcon", tint = Color.White)
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
                Spacer(modifier = Modifier.height(15.dp))

                //AÑADIR PRODUCTOS
                Button(
                    onClick = { navController.navigate(AppScreens.AnadirTrabajador.ruta) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(300.dp)
                        .height(100.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                ) {
                    Text(
                        text = "AÑADIR TRABAJADORES",
                        fontSize = 25.sp,
                    )
                }

                Spacer(modifier = Modifier.height(15.dp))

                Button(
                    onClick = { navController.navigate(AppScreens.Despensa.ruta) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(300.dp)
                        .height(100.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                ) {
                    Text(
                        text = "DESPENSA",
                        fontSize = 25.sp,
                    )
                }

                Spacer(modifier = Modifier.height(15.dp))

                Button(
                    onClick = { navController.navigate(AppScreens.MESAS.ruta) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(300.dp)
                        .height(100.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                ) {
                    Text(
                        text = "MESAS",
                        fontSize = 25.sp,
                    )
                }
            }
        }
    }
}
