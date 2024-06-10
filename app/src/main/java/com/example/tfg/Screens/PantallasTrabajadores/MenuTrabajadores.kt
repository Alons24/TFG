package com.example.tfg.Screens.PantallasTrabajadores

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tfg.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuBotones(navController: NavHostController) {
        
        //Fin del cajón lateral y enpieza el Scaffold
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Color.Blue, // Cambia el color de fondo
                        titleContentColor = Color.White, // Cambia el color del título
                    ),
                    title = {
                        Text("MENU TRABAJADORES")
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigate("MenuPrimero") }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Localized description",
                                tint=Color.White
                            )
                        }
                    },
                    /*actions = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Menu",
                                tint=Color.White
                            )
                        }
                    }
                    */
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
                        onClick = {navController.navigate("Perfil")},
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
                        fontSize = 40.sp,
                    )
                }

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
                        fontSize = 40.sp,
                    )
                }

                Button(
                    onClick = { navController.navigate(AppScreens.ConsultarCriticaTrabajadores.ruta) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(300.dp)
                        .height(100.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                ) {
                    Text(
                        text = "Ver reseñas",
                        fontSize = 40.sp,
                    )
                }

                Button(
                    onClick = { navController.navigate(AppScreens.ConsultarReservaTrabajadores.ruta) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(300.dp)
                        .height(100.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                ) {
                    Text(
                        text = "Consultar reservas",
                        fontSize = 30.sp,
                    )
                }
            }
        }
    }