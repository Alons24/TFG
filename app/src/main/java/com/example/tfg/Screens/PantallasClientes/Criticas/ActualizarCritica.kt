package com.example.tfg.Screens.PantallasClientes.Criticas


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.tfg.Retrofit.DataClases.Critica
import com.example.tfg.Retrofit.SessionManager
import com.example.tfg.Retrofit.ViewModels.CriticaViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ActualizarCritica(navController: NavHostController, viewModel: CriticaViewModel) {

    val userEmail = SessionManager.getEmail(LocalContext.current)
    val nombreUsuario = SessionManager.getUserName(LocalContext.current)
    val firestore = FirebaseFirestore.getInstance()
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    var criticas by remember { mutableStateOf<List<Critica>>(emptyList()) }

    LaunchedEffect(key1 = userEmail) {
        firestore.collection("Critica")
            .whereEqualTo("nombreUsuario", nombreUsuario)
            .get()
            .addOnSuccessListener { documents ->
                val criticasList = mutableListOf<Critica>()
                for (document in documents) {
                    criticasList.add(document.toObject(Critica::class.java))
                }
                criticas = criticasList
            }
            .addOnFailureListener { exception ->
                // Handle any errors here
            }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.Blue, // Cambia el color de fondo
                    titleContentColor = Color.White, // Cambia el color del título
                ),
                title = {
                    androidx.compose.material3.Text("VER CRÍTICAS")
                },
                navigationIcon = {
                    androidx.compose.material3.IconButton(onClick = { navController.popBackStack() }) {
                        androidx.compose.material3.Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    androidx.compose.material3.IconButton(onClick = { scope.launch { drawerState.open() } }) {
                        androidx.compose.material3.Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Menu",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        bottomBar = {
            androidx.compose.material3.BottomAppBar(
                containerColor = Color.Blue,
                contentColor = androidx.compose.material3.MaterialTheme.colorScheme.primary,
            ) {
                // Iconos de navegación
                BottomNavigationItem(
                    selected = false,
                    onClick = { /*TIENE QUE ENVIAR LA INFORMACIÓN A LA BASE DE DATOS TRAS PULDAR*/ },
                    modifier = Modifier.weight(1f),
                    icon = {
                        androidx.compose.material3.Icon(
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
                        androidx.compose.material3.Icon(
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
                        androidx.compose.material3.Icon(
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
                        androidx.compose.material3.Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "ENVIAR",
                            tint = Color.White
                        )
                    },
                )
            }
        },
    ) { innerPadding ->
        // Aquí puedes agregar el contenido principal de tu pantalla
        // El parámetro innerPadding contiene el padding necesario para que tu contenido no se superponga con la TopAppBar y la BottomAppBar
        ModificarCriticasScreen(viewModel, navController, criticas)
    }
}

@Composable
fun ModificarCriticasScreen(viewModel: CriticaViewModel, navController: NavHostController, criticas: List<Critica>) {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        items(criticas) { critica ->
            ModificarCriticaItem(critica, viewModel, navController)
        }
    }
}

@Composable
fun ModificarCriticaForm(critica: Critica?, onCriticaModified: (Critica) -> Unit) {
    if (critica != null) {

        var idCritica by remember { mutableStateOf(critica.idCritica) }
        var nombreUsuario by remember { mutableStateOf(critica.nombreUsuario) }
        var mensaje by remember { mutableStateOf(critica.mensaje) }
        var valoracion by remember { mutableStateOf(critica.valoracion) }


        Column {
            Text("ID de la crítica")
            TextField(value = idCritica, onValueChange = { idCritica = it })

            Text("Nombre del cliente")
            TextField(value = nombreUsuario, onValueChange = { nombreUsuario = it })

            Text("Mensaje de la crítica")
            TextField(value = mensaje, onValueChange = { mensaje = it })

            Text("Valoración de la crítica")
            Slider(
                value = valoracion,
                onValueChange = { valoracion = it },
                valueRange = 1f..5f,
                steps = 4,
                modifier = Modifier.fillMaxWidth(0.8f)
            )


            Button(onClick = {
                val modifiedCritica = Critica(
                    idCritica = idCritica,
                    nombreUsuario = nombreUsuario,
                    mensaje = mensaje,
                    valoracion = valoracion
                )
                onCriticaModified(modifiedCritica)
            }) {
                Text("Modificar Crítica")
            }
        }
    } else {
        Text("La crítica no existe o no se pudo recuperar.")
    }
}

@Composable
fun ModificarCriticaItem(
    critica: Critica,
    viewModel: CriticaViewModel,
    navController: NavHostController,
) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            ModificarCriticaForm(critica) { modifiedCritica ->
                viewModel.actualizarCritica(modifiedCritica, modifiedCritica.idCritica)
                try {
                    showDialog = false
                    navController.popBackStack()
                } catch (e: Exception) {
                    Log.e("Error", "Ha ocurrido un error después de modificar la critiica: ${e.message}")
                }
            }
        }
    }

    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White,
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable { showDialog = true }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "ID de la crítica: ${critica.idCritica}", style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Nombre del cliente: ${critica.nombreUsuario}", style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Mensaje de la crítica: ${critica.mensaje}", style = MaterialTheme.typography.body2)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Valoración de la crítica: ${critica.valoracion}", style = MaterialTheme.typography.body2)
        }
    }
}