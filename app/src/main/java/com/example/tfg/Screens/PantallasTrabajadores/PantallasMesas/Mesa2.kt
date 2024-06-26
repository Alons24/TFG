package com.example.tfg.Screens.PantallasTrabajadores.PantallasMesas

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
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
import androidx.compose.material3.OutlinedTextField
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
fun Mesa2(navController: NavHostController) {
    var productosEncargados by remember { mutableStateOf(false) }
    val db = FirebaseFirestore.getInstance()
    val coleccion = "Mesa2"
    var datos by remember { mutableStateOf("") }
    var mensajeConfirmacion by remember { mutableStateOf("") }

    val scaffoldState = rememberScrollState()
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

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

                    Button(
                        onClick = { navController.navigate(AppScreens.Despensa.ruta) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                    ) {
                        Text(
                            text = "DESPENSA",
                            fontSize = 50.sp,
                        )
                    }

                    Button(
                        onClick = { /*navController.navigate(AppScreens.Despensa.ruta)*/ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                    ) {
                        Text(
                            text = "VER RESEÑAS",
                            fontSize = 40.sp,
                        )
                    }

                    Button(
                        onClick = { navController.navigate(AppScreens.ConsultarReservaTrabajadores.ruta) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                    ) {
                        Text(
                            text = "CONSULTAR RESERVAS",
                            fontSize = 30.sp,
                        )
                    }

                }
            }
        },
    ) {
        // Fin de los botones del menú lateral
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Color.Blue, // Cambia el color de fondo
                        titleContentColor = Color.White, // Cambia el color del título
                    ),
                    title = {
                        Text("MESA 2")
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigate(AppScreens.MESAS.ruta) }) {
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
                    // Iconos de navegación

                    BottomNavigationItem(
                        selected = false,
                        onClick = { /*TIENE QUE ENVIAR LA INFORMACIÓN A LA BASE DE DATOS TRAS PULDAR*/ },
                        modifier = Modifier.weight(1f),
                        icon = {
                            Icon(imageVector = Icons.Default.AccountBox, contentDescription = "TICKET", tint = Color.White)
                        },
                    )

                    BottomNavigationItem(
                        selected = false,
                        onClick = { /*TIENE QUE ENVIAR LA INFORMACIÓN A LA BASE DE DATOS TRAS PULDAR*/ },
                        modifier = Modifier.weight(1f),
                        icon = {
                            Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "COBRAR", tint = Color.White)
                        },
                    )

                    BottomNavigationItem(
                        selected = false,
                        onClick = { /*TIENE QUE ENVIAR LA INFORMACIÓN A LA BASE DE DATOS TRAS PULDAR*/ },
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
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    val productosMesa2 = remember { mutableStateOf(emptyList<Map<String, Any>>()) }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.mesa3tuneada2),
                            contentDescription = "MESA 2",
                            modifier = Modifier
                                .padding(22.dp)
                                .height(500.dp)
                                .width(300.dp)
                        )

                        var NumComensales by remember { mutableStateOf("") }
                        OutlinedTextField(
                            value = NumComensales,
                            onValueChange = { NumComensales = it },
                            singleLine = true,
                            label = {
                                Text("¿Cuántos comensales?")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            textStyle = TextStyle(fontSize = 18.sp),
                        )

                        // Aquí organizamos los botones en filas de dos
                        val botones = listOf(
                            Triple("Tostas", AppScreens.CartaTostasTrabajadores2.ruta, Color(4, 104, 249, 255)),
                            Triple("Bebidas", AppScreens.CartaBebidasTrabajadores.ruta, Color(128, 0, 128, 255)),
                            Triple("Bocadillos", AppScreens.CartaEntrantes.ruta, Color(24, 119, 37, 255)),
                            Triple("Cafés", AppScreens.CartaEntrantes.ruta, Color(255, 128, 0, 255)),
                            Triple("Cervezas", AppScreens.CartaEntrantes.ruta, Color(20, 183, 25, 255)),
                            Triple("Entrantes", AppScreens.CartaEntrantes.ruta, Color(202, 179, 3, 255)),
                            Triple("Raciones", AppScreens.CartaEntrantes.ruta, Color(255, 0, 0, 255)),
                            Triple("Pinchos", AppScreens.CartaEntrantes.ruta, Color(24, 119, 37, 255))
                        )

                        botones.chunked(2).forEach { fila ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                fila.forEach { (texto, ruta, color) ->
                                    Button(
                                        onClick = { navController.navigate(ruta) },
                                        modifier = Modifier
                                            .weight(1f)
                                            .height(100.dp),
                                        shape = RectangleShape,
                                        colors = ButtonDefaults.buttonColors(color)
                                    ) {
                                        Text(
                                            text = texto,
                                            fontSize = 18.sp,
                                        )
                                    }
                                }
                            }
                        }

                        Button(
                            onClick = {
                                db.collection(coleccion)
                                    .add(hashMapOf("NumComensales" to NumComensales))
                                    .addOnSuccessListener {
                                        mensajeConfirmacion = "Comensales encargada correctamente :)"
                                        NumComensales = ""
                                    }
                                    .addOnFailureListener {
                                        mensajeConfirmacion = "No se han podido guardar los comensales :("
                                    }

                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF4CAF50),
                                contentColor = Color.Yellow
                            ),
                            border = BorderStroke(1.dp, Color.Black)
                        ) {
                            Text(text = "ENVIAR")
                        }
                    }

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
                                productosMesa2.value = resultado.documents.map { it.data ?: emptyMap() }
                                productosEncargados = productosMesa2.value.isNotEmpty()
                            }
                            .addOnFailureListener {
                                datos = "No ha podido conectar"
                            }
                    }

                    if (productosEncargados) {
                        productosMesa2.value.forEach { pedido ->
                            pedido.forEach { (key, value) ->
                                when (key) {
                                    "tosta" -> {
                                        Text(
                                            text = "Producto: $value",
                                            style = TextStyle(
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        )
                                    }
                                    "NumComensales" -> {
                                        Text(
                                            text = "Número de comensales: $value",
                                            style = TextStyle(
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        )
                                    }
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
