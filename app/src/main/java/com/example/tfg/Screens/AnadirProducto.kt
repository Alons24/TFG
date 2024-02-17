package com.example.tfg.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.tfg.navigation.AppScreens
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnadirProducto(navController: NavHostController){
    var id by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }

    var mensajeConfirmacion by remember { mutableStateOf("") }
    val db = FirebaseFirestore.getInstance()
    val coleccion = "despensa"


/*TENGO QUE METER EL CAJÓN LATERAL*/

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.Green, // Cambia el color de fondo
                    titleContentColor = Color.Black, // Cambia el color del título
                ),
                title = {
                    Text("GUARDAR PRODUCTOS")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("MenuInicio") }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate("MenuInicio") }) {
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = id,
                onValueChange = { id = it },
                singleLine = true,
                label = {
                    Text("Introduzca su id")
                }
            )

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                singleLine = true,
                label = {
                    Text("Introduzca su nombre")
                }
            )


            Spacer(modifier = Modifier.height(15.dp))

            Button(

                onClick = {

                    db.collection(coleccion)
                        .document(id)
                        .set(hashMapOf(
                            "id" to id,
                            "nombre" to nombre,

                        ))
                        .addOnSuccessListener {
                            mensajeConfirmacion = "Datos guardados correctamente :)"
                            id = ""
                            nombre = ""

                        }
                        .addOnFailureListener {
                            mensajeConfirmacion = "No se ha podido guardar :("
                            id = ""
                            nombre = ""

                        }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50),
                    contentColor = Color.Yellow
                ),
                border = BorderStroke(1.dp, Color.Black)
            ) {
                Text(text = "Guardar")
            }

            Spacer(modifier = Modifier.size(10.dp))
            Text(text = mensajeConfirmacion)
        }
    }


}