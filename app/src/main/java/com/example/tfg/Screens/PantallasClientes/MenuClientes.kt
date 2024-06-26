package com.example.tfg.Screens.PantallasClientes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tfg.Retrofit.SessionManager
import com.example.tfg.navigation.AppScreens
import com.google.firebase.firestore.FirebaseFirestore


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuClientes(navController: NavHostController) {

    val context = LocalContext.current

    val db = FirebaseFirestore.getInstance()
    val coleccion = "User"
    val emailUsuario = SessionManager.getEmail(context)
    val nombreUsuario = SessionManager.getUserName(context)
    val scaffoldState = rememberScrollState()
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val showDialog = remember { mutableStateOf(false) }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text("Cerrar sesión") },
            text = { Text("¿Estás seguro de que quieres cerrar la sesión?") },
            confirmButton = {
                Button(onClick = {
                    showDialog.value = false
                    SessionManager.clear(context)
                    navController.navigate(AppScreens.LoginScreen.ruta)
                }) {
                    Text("Sí")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog.value = false }) {
                    Text("No")
                }
            }
        )
    }

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
                    //BOTÓN PARA VOLVER AL MENÚ DE INICIO
                    // Otros elementos del menú lateral
                    Button(
                        onClick = { navController.navigate(AppScreens.CrearCritica.ruta) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .width(300.dp)
                            .height(100.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                    ) {
                        Text(
                            text = "EJEMPLO",
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
                        Text("Bienvenido/a $nombreUsuario", color = Color.White)
                    },
                    navigationIcon = {
                        IconButton(onClick = {  showDialog.value = true }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Localized description",
                                tint= Color.White
                            )
                        }
                    },
                    /*actions = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Menu",
                                tint= Color.White
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
                    // Icono
                    BottomNavigationItem(
                        selected = false,
                        onClick = {navController.navigate("Reservas")},
                        modifier = Modifier.weight(1f),
                        icon = {
                            Icon(imageVector = Icons.Default.DateRange, contentDescription = "Search", tint = Color.White)
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
                    .verticalScroll(rememberScrollState()) // Habilitar desplazamiento vertical
                    .background(MaterialTheme.colorScheme.background.copy(alpha = 0.8f))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {



                Button(
                    onClick = { navController.navigate(AppScreens.MenuDelDia.ruta) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(300.dp)
                        .height(100.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                ) {
                    Text(
                        text = "Menú del día",
                        fontSize = 30.sp,
                    )
                }



                Button(
                    onClick = { /*navController.navigate(AppScreens.DejarResena.ruta)*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(300.dp)
                        .height(100.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                ) {
                    Text(
                        text = "Escaner QR",
                        fontSize = 30.sp,
                    )
                }

                Button(
                    onClick = { navController.navigate(AppScreens.MenuCategorias.ruta) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(300.dp)
                        .height(100.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                ) {
                    Text(
                        text = "Menú Platos",
                        fontSize = 30.sp,
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
                        text = "Menú Reservas",
                        fontSize = 30.sp,
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
                        text = "Menú Críticas",
                        fontSize = 30.sp,
                    )
                }



            }
        }
    }
}
