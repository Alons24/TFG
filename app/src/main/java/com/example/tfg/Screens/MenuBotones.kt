package com.example.tfg.Screens

import com.example.tfg.navigation.AppScreens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuBotones(navController: NavHostController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("MENÚ DE CLIENTES 1")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("Menu") }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate("Menu") }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(MaterialTheme.colorScheme.background.copy(alpha = 0.8f))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(15.dp))


                //AÑADIR PRODUCTOS
                Button(onClick = { navController.navigate(AppScreens.AnadirProducto.ruta) },
                    modifier = Modifier.width(300.dp)
                ) {
                    Text(
                        text = "AÑADIR PRODUCTOS",
                        fontSize = 25.sp,
                    )
                }
                //
                Spacer(modifier = Modifier.height(15.dp))

                Button(
                    onClick = { navController.navigate("Despensa") },
                    modifier = Modifier.width(300.dp)
                ) {
                    Text(
                        text = "DESPENSA",
                        fontSize = 25.sp,
                    )
                }

                Spacer(modifier = Modifier.height(15.dp))

                Button(onClick = { navController.navigate(AppScreens.MESAS.ruta) },
                    modifier = Modifier.width(300.dp)
                ) {
                    Text(
                        text = "MESAS",
                        fontSize = 25.sp,
                    )
                }

            }
        }
    )
}
