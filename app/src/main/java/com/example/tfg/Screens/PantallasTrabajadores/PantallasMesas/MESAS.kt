package com.example.tfg.Screens.PantallasTrabajadores.PantallasMesas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tfg.R
import com.example.tfg.navigation.AppScreens
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Mesas(navController: NavHostController) {
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
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Color.Blue,
                        titleContentColor = Color.White,
                    ),
                    title = { Text("MESAS") },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigate("MenuBotones") }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
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
                    BottomNavigationItem(
                        selected = false,
                        onClick = { navController.navigate(AppScreens.Perfil.ruta) },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = "Profile",
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
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    val mesas = listOf(
                        Triple("1", AppScreens.Mesa1.ruta, R.drawable.mesa3tuneada1),
                        Triple("2", AppScreens.Mesa2.ruta, R.drawable.mesa3tuneada2),
                        Triple("3", AppScreens.Mesa3.ruta, R.drawable.mesa3tuneada3),
                        Triple("4", AppScreens.Mesa4.ruta, R.drawable.mesa3tuneada4),
                        Triple("5", AppScreens.Mesa5.ruta, R.drawable.mesa3tuneada5),
                        Triple("6", AppScreens.Mesa6.ruta, R.drawable.mesa3tuneada6),
                        Triple("7", AppScreens.Mesa7.ruta, R.drawable.mesa3tuneada7),
                        Triple("8", AppScreens.Mesa8.ruta, R.drawable.mesa3tuneada8),
                        Triple("9", AppScreens.Mesa9.ruta, R.drawable.mesa3tuneada9)
                    )

                    mesas.chunked(3).forEach { rowItems ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            rowItems.forEach { (mesaName, route, imageRes) ->
                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Image(
                                        painter = painterResource(imageRes),
                                        contentDescription = mesaName,
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .height(150.dp)
                                            .fillMaxWidth()
                                    )
                                    Button(
                                        onClick = { navController.navigate(route) },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(75.dp),
                                        shape = RectangleShape,
                                        colors = ButtonDefaults.buttonColors(Color(255, 215, 0, 255)) // Color dorado
                                    ) {
                                        Text(
                                            text = mesaName,
                                            fontSize = 40.sp,
                                            modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally) // Centrar texto
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
