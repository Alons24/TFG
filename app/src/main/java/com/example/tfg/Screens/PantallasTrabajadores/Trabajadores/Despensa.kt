package com.example.tfg.Screens.PantallasTrabajadores.Trabajadores

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
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
    val db = FirebaseFirestore.getInstance()
    val coleccion = "Despensa"
    var datos by remember { mutableStateOf("") }

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
                        onClick = {
                            // Navegar a la pantalla de inicio
                            navController.navigate("MenuInicio")
                            // Cerrar el cajón de navegación modal después de la navegación
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                    ) {
                        Text(
                            text = "ejemplo",
                            style = TextStyle(fontSize = 30.sp)
                        )
                    }

                    //FIN DE LOS BOTONES DEL MENÚ LATERAL
                }
            }
        },
    ) {

        //COMIENZO DEL SCAFFOLD
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Color.Blue, // Cambia el color de fondo
                        titleContentColor = Color.White, // Cambia el color del título
                    ),
                    title = {
                        Text("DESPENSA")
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigate("MenuPrimero") }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { (run { scope.launch { drawerState.open() } }) }) {
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
                    containerColor = Color.Blue,
                    contentColor = MaterialTheme.colorScheme.primary,
                ) {
                    // Icono
                    BottomNavigationItem(
                        selected = false,
                        onClick = {/*QUE HAGA ALGOOOOOOOOOOOOOOOO*/ },
                        modifier = Modifier.weight(1f),
                        icon = {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "Search",
                                tint = Color.White
                            )
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
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
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
                        val ref = db.collection("Despensa")

                        try {
                            val querySnapshot = ref.get().await()

                            for (document in querySnapshot) {
                                Log.d(
                                    "samuLino",
                                    "Datos del documento: ${document.data.toString()}"
                                )
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
                        productos.value.forEachIndexed { index, productos ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                                    .height(570.dp),
                                shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
                                colors = androidx.compose.material3.CardDefaults.cardColors(
                                    containerColor = Color.White
                                ),
                                elevation = androidx.compose.material3.CardDefaults.cardElevation(
                                    defaultElevation = 4.dp
                                )
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(16.dp),
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Text(
                                        text = "Productos: ",
                                        style = TextStyle(
                                            fontSize = 30.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )


                                    Text(
                                        text = "licores y destilados: ${productos["Brandy"]}",
                                        style = TextStyle(fontSize = 25.sp)
                                    )

                                    Text(
                                        text = "Refrescos: ${productos["Refrescos"]}",
                                        style = TextStyle(fontSize = 25.sp)
                                    )

                                    Text(
                                        text = "No alcholicos: ${productos["Refrescos"]}",
                                        style = TextStyle(fontSize = 25.sp)
                                    )

                                    Text(
                                        text = "Mahou: ${productos["Refrescos"]}",
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
