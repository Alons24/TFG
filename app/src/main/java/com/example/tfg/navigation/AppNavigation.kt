


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tfg.Screens.MenuPrimero
import com.example.tfg.Screens.PantallasClientes.CartaEntrantes
import com.example.tfg.Screens.PantallasClientes.DejarResena
import com.example.tfg.Screens.PantallasClientes.MenuClientes
import com.example.tfg.Screens.PantallasClientes.Reservas
import com.example.tfg.Screens.PantallasClientes.pantallaLogin
import com.example.tfg.Screens.PantallasTrabajadores.MenuBotones
import com.example.tfg.Screens.PantallasTrabajadores.PantallasMesas.Mesa
import com.example.tfg.Screens.PantallasTrabajadores.PantallasMesas.Mesa1
import com.example.tfg.Screens.PantallasTrabajadores.PantallasMesas.Mesa2
import com.example.tfg.Screens.PantallasTrabajadores.Trabajadores.Despensa
import com.example.tfg.Screens.Perfil
import com.example.tfg.navigation.AppScreens


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun AppNavigation() {
    val navigationController = rememberNavController()
    //val viewModel= NavHostController()
    //Declaramos en startDestination la pantalla en la que va a empezar.
    NavHost(navController = navigationController, startDestination = AppScreens.MenuPrimero.ruta)
    {
        composable(AppScreens.MenuPrimero.ruta) { MenuPrimero(navigationController) }
        /*Este es el menú de trabajadores*/composable(AppScreens.MenuBotones.ruta){ MenuBotones(navigationController) }
        composable(AppScreens.Despensa.ruta){ Despensa(navigationController) }
        composable(AppScreens.MESAS.ruta){ Mesa(navigationController) }
        composable(AppScreens.Mesa1.ruta){ Mesa1(navigationController) }
        composable(AppScreens.Mesa2.ruta){ Mesa2(navigationController) }
        composable(AppScreens.Mesa2.ruta){AnadirTrabajador(navigationController)}
        composable(AppScreens.MenuClientes.ruta){MenuClientes(navigationController)}
        composable(AppScreens.InicioSesion.ruta){InicioSesion(navigationController)}
        composable(AppScreens.pantallaLogin.ruta){pantallaLogin(navigationController) }
        composable(AppScreens.Perfil.ruta){ Perfil(navigationController)}
        composable(AppScreens.Carta.ruta){ CartaEntrantes(navigationController) }
        composable(AppScreens.MenuCategorias.ruta){ MenuCategorias(navigationController)}
        composable(AppScreens.Reservas.ruta){ Reservas(navigationController)}
        composable(AppScreens.DejarResena.ruta){ DejarResena(navigationController)}



    //alonso bujarron


        }

}








