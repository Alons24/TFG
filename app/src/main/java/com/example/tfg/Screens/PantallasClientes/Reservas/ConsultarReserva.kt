package com.example.tfg.Screens.PantallasClientes.Reservas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tfg.Retrofit.DataClases.Reserva
import com.example.tfg.Retrofit.SessionManager
import com.example.tfg.navigation.AppScreens
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsultarReserva(navController: NavHostController) {
    val userEmail = SessionManager.getEmail(LocalContext.current)
    val firestore = FirebaseFirestore.getInstance()

    val scaffoldState = rememberScrollState()
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    var reservas by remember { mutableStateOf<List<Reserva>>(emptyList()) }

    /* Inicio del cajón lateral */
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
                    // Botón para volver al menú de inicio
                    Button(
                        onClick = { /*navController.navigate(AppScreens.MESAS.ruta)*/ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                    ) {
                        androidx.compose.material3.Text(
                            text = "RESERVAR",
                            fontSize = 25.sp,
                        )
                    }

                    Button(
                        onClick = { /*navController.navigate(AppScreens.Despensa.ruta) */},
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                    ) {
                        androidx.compose.material3.Text(
                            text = "ELIMINAR",
                            fontSize = 25.sp,
                        )
                    }


                    Button(
                        onClick = { /*navController.navigate(AppScreens.Despensa.ruta) */},
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                    ) {
                        androidx.compose.material3.Text(
                            text = "ACTUALIZAR",
                            fontSize = 25.sp,
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
                        androidx.compose.material3.Text("VER RESERVAS")
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
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
                    contentColor = androidx.compose.material3.MaterialTheme.colorScheme.primary,
                ) {
                    // Iconos de navegación
                    BottomNavigationItem(
                        selected = false,
                        onClick = { /*TIENE QUE ENVIAR LA INFORMACIÓN A LA BASE DE DATOS TRAS PULDAR*/ },
                        modifier = Modifier.weight(1f),
                        icon = {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "OPCIONES",
                                tint = Color.White
                            )
                        },
                    )

                    BottomNavigationItem(
                        selected = false,
                        onClick = { /*TIENE QUE ENVIAR LA INFORMACIÓN A LA BASE DE DATOS TRAS PULDAR*/ },
                        modifier = Modifier.weight(1f),
                        icon = {
                            Icon(
                                imageVector = Icons.Default.AccountBox,
                                contentDescription = "TICKET",
                                tint = Color.White
                            )
                        },
                    )

                    BottomNavigationItem(
                        selected = false,
                        onClick = { /*TIENE QUE ENVIAR LA INFORMACIÓN A LA BASE DE DATOS TRAS PULDAR*/ },
                        modifier = Modifier.weight(1f),
                        icon = {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = "COBRAR",
                                tint = Color.White
                            )
                        },
                    )

                    BottomNavigationItem(
                        selected = false,
                        onClick = { /*TIENE QUE ENVIAR LA INFORMACIÓN A LA BASE DE DATOS TRAS PULDAR*/ },
                        modifier = Modifier.weight(1f),
                        icon = {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = "ENVIAR",
                                tint = Color.White
                            )
                        },
                    )
                }
            },
        ) { innerPadding ->

            LaunchedEffect(key1 = userEmail) {
                firestore.collection("Reserva")
                    .whereEqualTo("emailCliente", userEmail)
                    .get()
                    .addOnSuccessListener { documents ->
                        val reservasList = mutableListOf<Reserva>()
                        for (document in documents) {
                            reservasList.add(document.toObject(Reserva::class.java))
                        }
                        reservas = reservasList
                    }
                    .addOnFailureListener { exception ->
                        // Handle any errors here
                    }
            }

            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding) // Usar innerPadding proporcionado por Scaffold
                    .padding(16.dp) // Agregar padding adicional si es necesario
            ) {
                items(reservas) { reserva ->
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        backgroundColor = Color.White,
                        elevation = 4.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "ID de reserva: ${reserva.idReserva}",
                                style = MaterialTheme.typography.h6
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Nombre del cliente: ${reserva.nombreCliente}",
                                style = MaterialTheme.typography.body1
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Fecha de reserva: ${reserva.fechaReserva}",
                                style = MaterialTheme.typography.body2
                            )
                            // Agrega más campos según sea necesario
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewReservaItem() {
    val reserva = Reserva(
        idReserva = "123",
        nombreCliente = "Juan",
        fechaReserva = "2023-12-31"
        // Añade los demás campos según sea necesario
    )
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White,
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "ID de reserva: ${reserva.idReserva}",
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Nombre del cliente: ${reserva.nombreCliente}",
                style = MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Fecha de reserva: ${reserva.fechaReserva}",
                style = MaterialTheme.typography.body2
            )
            // Agrega más campos según sea necesario
        }
    }
}
