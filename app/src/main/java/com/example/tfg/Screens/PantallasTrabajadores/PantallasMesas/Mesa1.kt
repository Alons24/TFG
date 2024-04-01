package com.example.tfg.Screens.PantallasTrabajadores.PantallasMesas

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tfg.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


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
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profile",
                        modifier = Modifier.size(24.dp)
                    )
                    Text("Perfil")

                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Settings",
                        modifier = Modifier.size(24.dp)
                    )
                    Text("Configuración")


                    // Opción para cerrar sesión u otra acción
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                        contentDescription = "Logout",
                        modifier = Modifier.size(24.dp)
                    )
                    Text("Cerrar sesión")
                }
            }

        },
    ) {

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("MESA 1") },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigate("MenuInicio") }) {
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
                            style = TextStyle(color = Color.Black) // Ajustar color según sea necesario
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

                    /**/

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

                    // Adding the missing curly brace and fixing the misplaced modifier
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                    ) {
                        Text(
                            text = "LISTA:",
                            style = TextStyle(fontSize = 30.sp)


                        )
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
                                    /*Text(
                                        text = "Cliente ${index + 1}",
                                        style = TextStyle(
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )*/
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

                    /**/

                    /*IDEA: HACER QUE DESDE EL APARTADO DE MESA 1 (QUE CADA MESA TENGA UNO) SE PUEDAN METER PRODUCTOS.
                            * Y QUE SE GUARDEN EN UNA BASE DE DATOS (FIREBASE)*/
                }
            }
        }
    }
}