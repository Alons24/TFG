package com.example.tfg.Screens.PantallasTrabajadores.Trabajadores.CartaTrabajadores

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tfg.Retrofit.DataClases.Producto
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartaBebidasTrabajadores(navController: NavHostController) {


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
                        onClick = {
                            navController.navigate("Mesa1")
                            scope.launch { drawerState.close() }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                    ) {
                        Text(
                            text = "ejemplo",
                            style = TextStyle(fontSize = 30.sp)
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
                    title = { Text("CARTA BEBIDAS") },

                    /*EN REALIDAD NO DEBERÍA HABER UN BOTÓN QUE TE LLEVE ATRÁS
                    * SINO QUE DEBERÍAMOS DESLIZAR PARA QUE NOS LLEVE A LA ÚLTIMA MESA
                    * EN LA QUE HUBIÉRAMOS ESTADO Y NO A UNA DETERMINADA.*/

                    navigationIcon = {
                        /*IconButton(onClick = { navController.navigate("Mesa1") }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Localized description"
                            )
                        }*/
                    },
                    actions = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
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
                    containerColor = Color.Blue,
                    contentColor = MaterialTheme.colorScheme.primary,
                ) {
                    BottomNavigationItem(
                        selected = false,
                        onClick = { /* Acción del icono */ },
                        modifier = Modifier.weight(1f),
                        icon = {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "Search",
                                tint = Color.White
                            )
                        },
                    )

                    BottomNavigationItem(
                        selected = false,
                        onClick = { /* Acción del segundo icono */ },
                        modifier = Modifier.weight(1f),
                        icon = {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = "YourIcon",
                                tint = Color.White
                            )
                        },
                    )
                }
            },
        ) { innerPadding ->
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.padding(innerPadding)
            ) {


                items(listaProductos) {
                    ProductoDiseño(producto = it)
                }

            }
        }
    }
}




private val listaProductos = listOf(
    Producto(
        nombre = "Manzana",
        precio = 18.99,
        categoria = "",
        descripcion = "",
        idProducto = 1,
        imagen = ""
    ),
    Producto(
        nombre = "Pera",
        precio = 15.99,
        categoria = "",
        descripcion = "",
        idProducto = 2,
        imagen = ""
    ),
    Producto(
        nombre = "Naranja",
        precio = 12.99,
        categoria = "",
        descripcion = "",
        idProducto = 3,
        imagen = ""
    ),
    Producto(
        nombre = "Plátano",
        precio = 9.99,
        categoria = "",
        descripcion = "",
        idProducto = 4,
        imagen = ""
    ),
    Producto(
        nombre = "Fresa",
        precio = 8.99,
        categoria = "",
        descripcion = "",
        idProducto = 5,
        imagen = ""
    ),
    Producto(
        nombre = "Cereza",
        precio = 7.99,
        categoria = "",
        descripcion = "",
        idProducto = 6,
        imagen = ""
    ),
    Producto(
        nombre = "Mango",
        precio = 6.99,
        categoria = "",
        descripcion = "",
        idProducto = 7,
        imagen = ""
    ),
    Producto(
        nombre = "Piña",
        precio = 5.99,
        categoria = "",
        descripcion = "",
        idProducto = 8,
        imagen = ""
    ),
    Producto(
        nombre = "Melón",
        precio = 4.99,
        categoria = "",
        descripcion = "",
        idProducto = 9,
        imagen = ""
    ),
)



@Composable
fun ProductoDiseñoBebidas(producto: Producto) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = producto.nombre,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
        )

        Button(onClick = { /* Aquí va la acción del botón */ }) {
            Text(text = "Añadir")
        }

        Text(
            text = "${producto.precio} €",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle.Italic
            )
        )
    }
}

@Composable
fun PublicidadDiseñoBebidas(publicidad: Int) {
    Box(
        modifier = Modifier
            .size(140.dp)
            .clip(RoundedCornerShape(12))
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = publicidad),
            contentDescription = null,
            modifier = Modifier.size(140.dp)
        )
    }
}