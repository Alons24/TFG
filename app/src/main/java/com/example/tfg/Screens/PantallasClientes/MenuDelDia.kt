package com.example.tfg.Screens.PantallasClientes

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
fun MenuDelDia(navController: NavHostController) {

    var menu by remember { mutableStateOf(false) }

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
                            navController.navigate("MenuClientes")
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
                        Text("Menu del día")
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigate("MenuClientes") }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Localized description",
                                tint= Color.White
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { (run { scope.launch { drawerState.open() } }) }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Menu",
                                tint= Color.White
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
                        onClick = {navController.navigate("Reservas") },
                        modifier = Modifier.weight(1f),
                        icon = {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "Search",
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

                    val menuDia = remember { mutableStateOf(emptyList<Map<String, Any>>()) }


                    // Usamos LaunchedEffect para cargar los datos cuando la pantalla se carga por primera vez
                    LaunchedEffect(Unit) {
                        val db = FirebaseFirestore.getInstance()
                        val ref = db.collection("MenuDia")

                        try {
                            val querySnapshot = ref.get().await()

                            for (document in querySnapshot) {
                                Log.d(
                                    "samuLino",
                                    "Datos del documento: ${document.data.toString()}"
                                )
                            }

                            menuDia.value = querySnapshot.documents.map { it.data ?: emptyMap() }
                            menu = menuDia.value.isNotEmpty()
                        } catch (exception: Exception) {
                            // Ha ocurrido un error al realizar la consulta
                            Log.e("samuLino", "Error al realizar la consulta: ${exception.message}")
                            datos = "no se ha podido conectar"
                        }
                    }

                    if (menu) {
                        menuDia.value.forEach { platos ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                                    .fillMaxHeight(),
                                shape = RoundedCornerShape(8.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                ),
                                elevation = CardDefaults.cardElevation(
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
                                        text = "PRIMERO A ELEGIR: ",
                                        style = TextStyle(
                                            fontSize = 30.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )

                                    Text(
                                        text = " ${platos["Ensalada"]}",
                                        style = TextStyle(fontSize = 25.sp)
                                    )
                                    Spacer(modifier = Modifier.height(7.dp))

                                    Text(
                                        text = " ${platos["Gazpacho"]}",
                                        style = TextStyle(fontSize = 25.sp)
                                    )

                                    Spacer(modifier = Modifier.height(7.dp))
                                    Text(
                                        text = " ${platos["PaellaMixta"]}",
                                        style = TextStyle(fontSize = 25.sp)
                                    )
                                    Spacer(modifier = Modifier.height(7.dp))

                                    Text(
                                        text = " ${platos["Trigueros"]}",
                                        style = TextStyle(fontSize = 25.sp)
                                    )




                                    Spacer(modifier = Modifier.height(29.dp))

                                    Text(
                                        text = "SEGUNDO A ELEGIR: ",
                                        style = TextStyle(
                                            fontSize = 30.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                    Spacer(modifier = Modifier.height(7.dp))

                                    Text(
                                        text = " ${platos["medallones"]}",
                                        style = TextStyle(fontSize = 25.sp)
                                    )
                                    Spacer(modifier = Modifier.height(7.dp))

                                    Text(
                                        text = " ${platos["pechuga"]}",
                                        style = TextStyle(fontSize = 25.sp)
                                    )
                                    Spacer(modifier = Modifier.height(7.dp))

                                    Text(
                                        text = " ${platos["quesadilla"]}",
                                        style = TextStyle(fontSize = 25.sp)
                                    )
                                    Spacer(modifier = Modifier.height(7.dp))

                                    Text(
                                        text = " ${platos["Platija"]}",
                                        style = TextStyle(fontSize = 25.sp)
                                    )
                                    Spacer(modifier = Modifier.height(7.dp))

                                    Text(
                                        text = "Precio: ${platos["precio"]} €",
                                        style = TextStyle(
                                            fontSize = 25.sp,
                                            fontWeight = FontWeight.Bold
                                        )
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
