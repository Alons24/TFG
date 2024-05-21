package com.example.tfg.Screens.PantallasTrabajadores

import com.example.tfg.navigation.AppScreens
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
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

                    //FIN DE LOS BOTONES DEL MENÚ LATERAL
                }
            }
        },
    ) {
        
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
                        onClick = {},
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
                        fontSize = 25.sp,
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
                        fontSize = 25.sp,
                    )
                }
                /*
                no lo dudo jajajajaja

                les debes de tener acojonados a los pobres



                * */

                Button(
                    onClick = { navController.navigate(AppScreens.InicioSesion.ruta) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(300.dp)
                        .height(100.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                ) {
                    Text(
                        text = "INICIO DE SESIÓN",
                        fontSize = 25.sp,
                    )
                }


            }
        }
    }
}
