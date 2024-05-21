import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tfg.R
import com.example.tfg.navigation.AppScreens
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuCategorias(navController: NavHostController) {
    val scrollState = rememberScrollState()
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
                    //BOTÓN PARA BOLVER AL MENÚ DE INICIO
                    // Otros elementos del menú lateral
                    Button(
                        onClick = { navController.navigate(AppScreens.DejarResena.ruta) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .width(300.dp)
                            .height(100.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                    ) {
                        Text(
                            text = "EJEMPLO",
                            fontSize = 25.sp,
                        )
                    }
                    //FIN DE LOS BOTONES DEL MENÚ LATERAL
                }
            }
        },
    ) {

        //Fin del cajón lateral y enpieza el Scaffold
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Color.Blue, // Cambia el color de fondo
                        titleContentColor = Color.White, // Cambia el color del título
                    ),
                    title = {
                        Text("CATEGORÍAS")
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigate("MenuClientes") }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Localized description",
                                tint= Color.White
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Menu",
                                tint= Color.White
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
                    // Icono
                    BottomNavigationItem(
                        selected = false,
                        onClick = {navController.navigate("Perfil")},
                        modifier = Modifier.weight(1f),
                        icon = {
                            Icon(imageVector = Icons.Default.DateRange, contentDescription = "Search", tint = Color.White)
                        },
                    )

                    // Icono Adicional
                    BottomNavigationItem(
                        selected = false,
                        onClick = {navController.navigate("Perfil")},
                        modifier = Modifier.weight(1f),
                        icon = {
                            Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "YourIcon", tint = Color.White)
                        },
                    )
                }
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(MaterialTheme.colorScheme.background.copy(alpha = 0.8f))
                    .verticalScroll(scrollState) // Aquí se agrega el scroll
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(
                    onClick = { navController.navigate(AppScreens.Carta.ruta) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(300.dp)
                        .height(100.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                ) {
                    Text(
                        text = "ENTRANTES",
                        fontSize = 25.sp,
                    )
                }

                Button(
                    onClick = { /*navController.navigate(AppScreens.Despensa.ruta)*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(300.dp)
                        .height(100.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(Color(128, 0, 128, 255)) // Color morado
                ) {
                    Text(
                        text = "BEBIDAS",
                        fontSize = 25.sp,
                    )
                }

                Button(
                    onClick = { /*navController.navigate(AppScreens.Despensa.ruta)*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(300.dp)
                        .height(100.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(Color(24, 119, 37, 255)) // Color morado
                ) {
                    Text(
                        text = "BOCADILLOS",
                        fontSize = 25.sp,
                    )
                }

                Button(
                    onClick = { /*navController.navigate(AppScreens.Despensa.ruta)*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(300.dp)
                        .height(100.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(Color(255, 128, 0, 255)) // Color morado
                ) {
                    Text(
                        text = "CAFÉS",
                        fontSize = 25.sp,
                    )
                }

                Button(
                    onClick = { /*navController.navigate(AppScreens.Despensa.ruta)*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(300.dp)
                        .height(100.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(Color(255, 0, 0, 255)) // Color morado
                ) {
                    Text(
                        text = "CERVEZAS",
                        fontSize = 25.sp,
                    )
                }
            }
        }
    }
}
