import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnadirTrabajador(navController: NavHostController) {


    var id by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }

    var mensajeConfirmacion by remember { mutableStateOf("") }

    val db = FirebaseFirestore.getInstance()
    val coleccion = "trabajadores"
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

                    //Icono del perfil
                    IconButton(onClick = { navController.navigate("MenuBotones") }) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile",
                            modifier = Modifier.size(24.dp)
                        )
                        Text("Perfil")
                    }

                    //Icono Configuración
                    IconButton(onClick = { navController.navigate("MenuBotones") }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            modifier = Modifier.size(24.dp)
                        )
                        Text("Configuración")
                    }



                    IconButton(onClick = { "MenuBotones" }) {
                        // Opción para cerrar sesión u otra acción
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = "Logout",
                            modifier = Modifier.size(24.dp)
                        )
                        Text("Cerrar sesión")
                    }


                }
            }

        },
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Color.Green, // Cambia el color de fondo
                        titleContentColor = Color.Black, // Cambia el color del título
                    ),
                    title = {
                        Text("GUARDAR TRABAJADORES")
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigate("MenuInicio") }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { navController.navigate("MenuInicio") }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
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
                        //style = MaterialTheme.typography.caption
                    )
                }
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = id,
                    onValueChange = { id = it },
                    singleLine = true,
                    label = {
                        Text("Introduzca su ID")
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    singleLine = true,
                    label = {
                        Text("Introduzca su Nombre")
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = apellido,
                    onValueChange = { apellido = it },
                    singleLine = true,
                    label = {
                        Text("Introduzca su Apellido")
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        db.collection(coleccion)
                            .document(id)
                            .set(
                                hashMapOf(
                                    "id" to id,
                                    "nombre" to nombre,
                                    "apellido" to apellido,
                                )
                            )
                            .addOnSuccessListener {
                                mensajeConfirmacion = "Datos guardados correctamente :)"
                                id = ""
                                nombre = ""
                                apellido = ""
                            }
                            .addOnFailureListener {
                                mensajeConfirmacion = "No se ha podido guardar :("
                                id = ""
                                nombre = ""
                                apellido = ""
                            }
                    },
                    colors = ButtonDefaults.buttonColors(
                        //backgroundColor = Color(0xFF4CAF50),
                        contentColor = Color.Yellow
                    ),
                    border = BorderStroke(1.dp, Color.Black)
                ) {
                    Text(text = "Guardar Trabajador")
                }

                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = mensajeConfirmacion,
                    //style = MaterialTheme.typography.caption,
                    color = Color.Red
                )

            }
        }
    }
}