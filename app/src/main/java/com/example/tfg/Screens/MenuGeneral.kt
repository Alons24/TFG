package com.example.tfg.Screens

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
import com.example.tfg.navigation.AppScreens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuGeneral(navController: NavHostController) {
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
                //NO SE PORQUE NO SE PONE EL FONDO NEGRO
                //PREGUNTAR AL PROFE
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize() // Para ocupar todo el espacio disponible
                    .padding(16.dp) // Puedes ajustar el valor según tus necesidades
                    .background(MaterialTheme.colorScheme.background.copy(alpha = 0.8f)) // Color de fondo negro con opacidad
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {



                Spacer(modifier = Modifier.height(15.dp))



            }
        }
    )
}
