package com.example.tfg.Screens.PantallasTrabajadores.PantallasMesas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ShoppingCart
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tfg.R
import com.example.tfg.navigation.AppScreens
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Mesa1(navController: NavHostController) {
    var productosEncargados by remember { mutableStateOf(false) }
    val db = FirebaseFirestore.getInstance()
    val coleccion="Mesas"
    var datos by remember{ mutableStateOf("") }

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
                            text = "EJEMPLO",
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
                            text = "EJEMPLO 2",
                            fontSize = 25.sp,
                        )
                    }


                }
            }
        },
    ) {
        //FIN DE LOS BOTONES DEL MENÚ LATERAL

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Color.Blue, // Cambia el color de fondo
                        titleContentColor = Color.White, // Cambia el color del título
                    ),
                    title = {
                        Text("MESA 1")
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigate("MESAS") }) {
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
                        onClick = {/*TIENE QUE ENVIAR LA INFORMACIÓN A LA BASE DE DATOS
                        TRAS PULDAR*/},
                        modifier = Modifier.weight(1f),

                        icon = {
                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "OPCIONES", tint = Color.White)
                            ///*PROVISIONAL*/Text(text = "Opciones", fontSize = 20.sp, fontWeight = FontWeight.Black)
                            /*Estoy mirando como mejorarlo */
                        },
                    )


                    // Icono
                    BottomNavigationItem(
                        selected = false,
                        onClick = {/*TIENE QUE ENVIAR LA INFORMACIÓN A LA BASE DE DATOS
                        TRAS PULDAR*/},
                        modifier = Modifier.weight(1f),
                        icon = {
                            Icon(imageVector = Icons.Default.AccountBox, contentDescription = "TICKET", tint = Color.White)
                        },
                    )

                    // Icono
                    BottomNavigationItem(
                        selected = false,
                        onClick = {/*TIENE QUE ENVIAR LA INFORMACIÓN A LA BASE DE DATOS
                        TRAS PULDAR*/},
                        modifier = Modifier.weight(1f),
                        icon = {
                            Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "COBRAR", tint = Color.White)
                        },
                    )

                    // Icono
                    BottomNavigationItem(
                        selected = false,
                        onClick = {/*TIENE QUE ENVIAR LA INFORMACIÓN A LA BASE DE DATOS
                        TRAS PULDAR*/},
                        modifier = Modifier.weight(1f),
                        icon = {
                            Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "ENVIAR", tint = Color.White)
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
                    val productosMesa1= remember { mutableStateOf(emptyList<Map<String, Any>>()) }

                    // Card for MESA 1
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
                            Image(
                                painter = painterResource(R.drawable.mesa),
                                contentDescription = "MESA 1",
                                modifier = Modifier
                                    .padding(22.dp)
                                    .height(500.dp)
                                    .width(300.dp)
                            )
                        }
                    }

                    var NumComensales by remember { mutableStateOf("") }
                    OutlinedTextField(
                        value = NumComensales,
                        onValueChange = { NumComensales = it },
                        singleLine = true,
                        label = {
                            Text("¿Cuántos comensales? ")
                        }
                    )


                    /*AHORA ME DEBERÍA DE LLEVAR A UNA PANTALLA NUEVA CON MENÚ QUE CONTENGA LOS BOTONES DE:
                    * FUERA DE CARTA
                    * BOCADILLOS
                    * CERVEZAS
                    * TOSTADAS
                    * CAFES
                    * REFRESCOS*/



                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                    ) {
                        Text(text = "LISTA", fontSize = 30.sp, fontWeight = FontWeight.Bold)
                    }


                    // Usamos LaunchedEffect para cargar los datos cuando la pantalla se carga por primera vez
                    LaunchedEffect(Unit) {
                        datos = ""
                        db.collection(coleccion)
                            .get()
                            .addOnSuccessListener { resultado ->
                                productosMesa1.value =
                                    resultado.documents.map { it.data ?: emptyMap() }
                                productosEncargados = productosMesa1.value.isNotEmpty()
                            }
                            .addOnFailureListener {
                                datos = "No ha podido conectar"
                            }
                    }


                    if (productosEncargados) {
                        productosMesa1.value.forEachIndexed { index, pedido ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                                    .height(140.dp),
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(16.dp),
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {

                                    Text(
                                        text = "Cantidad productos: ${pedido["Tomates"]}",
                                        style = TextStyle(
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold)
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