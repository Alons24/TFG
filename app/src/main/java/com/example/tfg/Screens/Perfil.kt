package com.example.tfg.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tfg.R
import com.example.tfg.navigation.AppScreens
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Perfil(navController: NavHostController) {

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
                    // BOTÓN PARA VOLVER AL MENÚ DE INICIO
                    Button(
                        onClick = { navController.navigate(AppScreens.DejarResena.ruta) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                    ) {
                        Text(
                            text = "MESAS",
                            fontSize = 25.sp,
                        )
                    }

                    Button(
                        onClick = { navController.navigate(AppScreens.DejarResena.ruta) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                    ) {
                        Text(
                            text = "DESPENSA",
                            fontSize = 25.sp,
                        )
                    }


                    Button(
                        onClick = { navController.navigate(AppScreens.DejarResena.ruta) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                    ) {
                        Text(
                            text = "EJEMPLO",
                            fontSize = 25.sp,
                        )
                    }



                }
            }
        },
    ) {
        // Fin del cajón lateral y empieza el Scaffold
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Color.Blue, // Cambia el color de fondo
                        titleContentColor = Color.White, // Cambia el color del título
                    ),
                    title = {
                        Text("PERFIL")
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
        ) { innerPadding ->
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
                Image(
                    painter = painterResource(R.drawable.clienteicono),
                    contentDescription = "fotoPerfil",
                    modifier = Modifier
                        .padding(vertical = 22.dp)
                        .height(200.dp)
                        .width(200.dp)
                )

                var time by remember {
                    mutableStateOf(0L)
                }
                var isRunning by remember {
                    mutableStateOf(false)
                }
                var startTime by remember {
                    mutableStateOf(0L)
                }
                val keyboardController = LocalSoftwareKeyboardController.current

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = formatTime(time),
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(9.dp)
                    )
                    Spacer(modifier = Modifier.height(18.dp))
                    Row {
                        Button(
                            onClick = {
                                if (isRunning) {
                                    isRunning = false
                                } else {
                                    startTime = System.currentTimeMillis() - time
                                    isRunning = true
                                    keyboardController?.hide()
                                }
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = if (isRunning) "Pause" else "Start",
                                color = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Button(
                            onClick = {
                                time = 0
                                isRunning = false
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = "Reset", color = Color.White)
                        }
                    }
                }

                LaunchedEffect(isRunning) {
                    while (isRunning) {
                        delay(1000)
                        time = System.currentTimeMillis() - startTime
                    }
                }
            }
        }
    }
}

@Composable
fun formatTime(timeMi: Long): String {
    val hours = TimeUnit.MILLISECONDS.toHours(timeMi)
    val min = TimeUnit.MILLISECONDS.toMinutes(timeMi) % 60
    val sec = TimeUnit.MILLISECONDS.toSeconds(timeMi) % 60

    return String.format("%02d:%02d:%02d", hours, min, sec)
}
