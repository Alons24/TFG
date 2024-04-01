package com.example.tfg.Screens.PantallasTrabajadores.PantallasMesas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tfg.R

import com.example.tfg.navigation.AppScreens
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Mesa(navController: NavHostController) {
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
                TopAppBar(
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Color.Green,
                        titleContentColor = Color.Black,
                    ),
                    title = { Text("MESAS") },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigate("MenuBotones") }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { scope.launch { /*drawerState.open()*/ } }) {
                            Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
                        }
                    }
                )
            },
            bottomBar = {
                BottomAppBar(
                    content = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            text = "CONFIANZA, SEGURIDAD, SENCILLEZ",
                            style = TextStyle(color = Color.White) // Ajustar color según sea necesario
                        )
                    }
                )
            },
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(state = scaffoldState)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        /*INICIO*/
                        Image(
                            painter = painterResource(R.drawable.mesa),
                            contentDescription = "MESA 1",
                            modifier = Modifier
                                .padding(22.dp)
                                .height(500.dp)
                                .width(300.dp)
                        )

                        // Añade un botón al final de la Column

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
                                text = "Accede a Mesa 1",
                                fontSize = 25.sp,
                            )
                        }
                        /*FIN*/
                        Spacer(modifier = Modifier.height(16.dp))
                        /*INICIO*/
                        Image(
                            painter = painterResource(R.drawable.mesa),
                            contentDescription = "MESA 2",
                            modifier = Modifier
                                .padding(22.dp)
                                .height(500.dp)
                                .width(300.dp)
                        )


                        Button(
                            onClick = { navController.navigate(AppScreens.Mesa2.ruta) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .width(300.dp)
                                .height(100.dp),
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                        ) {
                            Text(
                                text = "Accede a Mesa 2",
                                fontSize = 25.sp,
                            )
                        }

                        /*FIN*/

                    }
                }
            }
        }
    }
}