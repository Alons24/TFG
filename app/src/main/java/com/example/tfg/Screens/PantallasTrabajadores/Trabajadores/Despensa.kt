package com.example.tfg.Screens.PantallasTrabajadores.Trabajadores

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Despensa(navController: NavHostController) {

    var productosEncontrados by remember { mutableStateOf(false) }
    var datos by remember { mutableStateOf("") }
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
            ){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Gray) // Puedes cambiar el color o usar otra imagen como fondo
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ){
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
                    IconButton(onClick = {navController.navigate("MenuBotones")}) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            modifier = Modifier.size(24.dp)
                        )
                        Text("Configuración")
                    }



                    IconButton(onClick = {"MenuBotones"}) {
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

        //COMIENZO DEL SCAFFOLD
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Color.Green,
                        titleContentColor = Color.Black,
                    ),
                    title = {
                        Text("DESPENSA")
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigate("MenuBotones") }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {(run { scope.launch { drawerState.open() } }) }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Localized description"
                            )
                        }
                    }
                )
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.primary,
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = " FÁCIL, PERSONAL, CONFIABLE",
                    )
                }
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
                    val productos = remember { mutableStateOf(emptyList<Map<String, Any>>()) }

                    // Usamos LaunchedEffect para cargar los datos cuando la pantalla se carga por primera vez
                    LaunchedEffect(Unit) {
                        val db = FirebaseFirestore.getInstance()
                        val ref = db.collection("/despensa")

                        try {
                            val querySnapshot = ref.get().await()

                            for (document in querySnapshot) {
                                Log.d("samuLino", "Datos del documento: ${document.data.toString()}")
                            }

                            productos.value = querySnapshot.documents.map { it.data ?: emptyMap() }
                            productosEncontrados = productos.value.isNotEmpty()
                        } catch (exception: Exception) {
                            // Ha ocurrido un error al realizar la consulta
                            Log.e("samuLino", "Error al realizar la consulta: ${exception.message}")
                            datos = "no se ha podido conectar"
                        }
                    }



                    if (productosEncontrados) {
                        productos.value.forEachIndexed { index, producto ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                                    .height(390.dp),
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(16.dp),
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Text(
                                        text = "Cantidad de productos: ",
                                        style = TextStyle(
                                            fontSize = 30.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                    /*DEBERÍAMOS PONER SOLO COSAS SLOTEBALES*/
                                    //Como por ejemplo Cervezas o botellas de Cocacolas

                                    Text(
                                        text = "Cantidad Patata: ${producto["CantidadPatatas"]}",
                                        style = TextStyle(fontSize = 25.sp)
                                    )

                                    Text(
                                        text = "Cantidad Pimientos: ${producto["CantidadPimientos"]}",
                                        style = TextStyle(fontSize = 25.sp)
                                    )

                                    Text(
                                        text = "Cantidad Tomates: ${producto["CantidadTomates"]}",
                                        style = TextStyle(fontSize = 25.sp)
                                    )
                                }
                            }
                        }
                    } else {
                        Text(
                            text = datos,
                            style = TextStyle(fontSize = 16.sp, color = Color.Red)
                        )
                    }
                }
            }
        }
    }
}
