    package com.example.tfg.Screens

    import android.util.Log
    import androidx.compose.foundation.Image
    import androidx.compose.foundation.layout.*
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.width
    import androidx.compose.material3.Button

    import androidx.compose.material3.Text
    import com.example.tfg.R
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp
    import androidx.navigation.NavHostController

    import androidx.compose.foundation.layout.height
    import androidx.compose.foundation.rememberScrollState
    import androidx.compose.foundation.verticalScroll
    import androidx.compose.material.icons.Icons
    import androidx.compose.material.icons.filled.ArrowBack
    import androidx.compose.material.icons.filled.Menu
    import androidx.compose.material3.BottomAppBar
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
    import androidx.compose.material3.TopAppBar
    import androidx.compose.material3.TopAppBarDefaults
    import androidx.compose.material3.rememberDrawerState

    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.remember
    import androidx.compose.runtime.rememberCoroutineScope
    import androidx.compose.runtime.setValue
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.text.TextStyle
    import androidx.compose.ui.text.font.FontWeight
    import androidx.compose.ui.text.style.TextAlign
    import com.google.firebase.firestore.FirebaseFirestore
    import kotlinx.coroutines.launch
    import kotlinx.coroutines.tasks.await


    //EN ESTA PANTALLA VEREMOS LOS PRODUCTOS QUE HAY EN LA DESPENSA

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Despensa(navController: NavHostController) {

        var productosEncontrados by remember { mutableStateOf(false) }

        var datos by remember { mutableStateOf("") }
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
                        //BOTÓN PARA BOLVER AL MENÚ DE INICIO
                        // Otros elementos del menú lateral
                        Button(
                            onClick = {
                                // Navegar a la pantalla de inicio
                                navController.navigate("MenuInicio")
                                // Cerrar el cajón de navegación modal después de la navegación
                                scope.launch {
                                    drawerState.close()
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp),
                        ) {
                            Text(
                                text = "Inicio",
                                style = TextStyle(fontSize = 30.sp)
                            )
                        }

                        //BOTÓN PARA IR A GUARDAR CLIENTE
                        Button(
                            onClick = {
                                // Navegar a la pantalla de inicio
                                navController.navigate("MenuInicio")
                                // Cerrar el cajón de navegación modal después de la navegación
                                scope.launch {
                                    drawerState.close()
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp),
                        ) {
                            Text(
                                text = "Guardar cliente",
                                style = TextStyle(fontSize = 30.sp)
                            )
                        }
                        //BOTÓN PARA IR A MODIFICAR CLIENTE
                        Button(
                            onClick = {
                                // Navegar a la pantalla de inicio
                                navController.navigate("MenuInicio")
                                // Cerrar el cajón de navegación modal después de la navegación
                                scope.launch {
                                    drawerState.close()
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp),
                        ) {
                            Text(
                                text = "Modificar cliente",
                                style = TextStyle(fontSize = 30.sp)
                            )
                        }

                        //BOTÓN PARA IR A CLIENTE INFORME
                        Button(
                            onClick = {
                                // Navegar a la pantalla de inicio
                                navController.navigate("MenuInicio")
                                // Cerrar el cajón de navegación modal después de la navegación
                                scope.launch {
                                    drawerState.close()
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp),
                        ) {
                            Text(
                                text = "Informar de los productos",
                                style = TextStyle(fontSize = 20.sp)
                            )
                        }

                        //BOTÓN PARA IR A CONSULTAR CLIENTE
                        Button(
                            onClick = {
                                // Navegar a la pantalla de inicio
                                navController.navigate("MenuInicio")
                                // Cerrar el cajón de navegación modal después de la navegación
                                scope.launch {
                                    drawerState.close()
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp),
                        ) {
                            Text(
                                text = "Consultar cliente",
                                style = TextStyle(fontSize = 30.sp)
                            )
                        }

                        //BOTÓN PARA IR A BORRAR CLIENTE
                        Button(
                            onClick = {
                                // Navegar a la pantalla de inicio
                                navController.navigate("MenuInicio")
                                // Cerrar el cajón de navegación modal después de la navegación
                                scope.launch {
                                    drawerState.close()
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp),
                        ) {
                            Text(
                                text = "BORRAR",
                                style = TextStyle(fontSize = 30.sp)
                            )
                        }

                        //FIN DE LOS BOTONES DEL MENÚ LATERAL
                    }
                }
            },
        ) {

            //FIN DEL MENÚ LATERAL
            //FIN DEL MENÚ LATERAL
            //FIN DEL MENÚ LATERAL

            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        colors = TopAppBarDefaults.mediumTopAppBarColors(
                            containerColor = Color.Green, // Color de fonde del Scaffold
                            titleContentColor = Color.Black, // Color del título.
                        ),
                        title = {
                            Text("DESPENSA")
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
                        val productos = remember { mutableStateOf(emptyList<Map<String, Any>>()) }

                        Button(
                            onClick = {
                                //Log.d de prueba
                                val db = FirebaseFirestore.getInstance()
                                Log.d("lino",db.toString())
                                val ref = db.collection("/despensa")
                                Log.d("lino",ref.toString())
                                ref.get()
                                    .addOnSuccessListener { querySnapshot ->

                                        for (document in querySnapshot) {
                                            Log.d("samuLino", "Datos del documento: ${document.data.toString()}")
                                        }
                                    }
                                    .addOnFailureListener { exception ->
                                        // Ha ocurrido un error al realizar la consulta
                                        Log.e("samuLino", "Error al realizar la consulta: ${exception.message}")
                                    }

                                //

                                    .addOnSuccessListener{ resultado->
                                        productos.value=
                                            resultado.documents.map{it.data ?: emptyMap() }
                                        productosEncontrados=productos.value.isNotEmpty()
                                    }
                                    .addOnFailureListener{
                                        datos="no se ha podido conectar"
                                    }

                                //


                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp),
                        ) {
                            Text(
                                text = "CONSULTAR DESPENSA",
                                style = TextStyle(fontSize = 16.sp)
                            )
                        }

                        if (productosEncontrados) {
                            productos.value.forEachIndexed { index, producto ->
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
                                        Text(
                                            text = "DESPENSA: ",
                                            //${index + 1}
                                            style = TextStyle(
                                                fontSize = 30.sp,
                                                fontWeight = FontWeight.Bold
                                            )


                                        )

                                        Text(

                                            text = "Cantidad Tarta: ${producto["CantidadTarta"]}",
                                            style = TextStyle(fontSize = 20.sp)
                                        )

                                        Text(
                                            text = "Cantidad Tarta: ${producto["CantidadTarta"]}",
                                            style = TextStyle(fontSize = 20.sp)
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
