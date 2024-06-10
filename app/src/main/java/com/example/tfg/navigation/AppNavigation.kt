


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tfg.INICIOSESIONYREGISTRO.LoginScreen
import com.example.tfg.INICIOSESIONYREGISTRO.MenuPrimero
import com.example.tfg.INICIOSESIONYREGISTRO.pantallaRegistro
import com.example.tfg.Screens.PantallasClientes.CartaTostasCliente
import com.example.tfg.Screens.PantallasClientes.CrearCritica
import com.example.tfg.Screens.PantallasClientes.Criticas.MenuCritica
import com.example.tfg.Screens.PantallasClientes.MenuClientes
import com.example.tfg.Screens.PantallasClientes.MenuDelDia
import com.example.tfg.Screens.PantallasClientes.Reservas.ConsultarReserva
import com.example.tfg.Screens.PantallasClientes.Reservas.Reservas
import com.example.tfg.Screens.PantallasTrabajadores.ConsultarReservaTrabajadores
import com.example.tfg.Screens.PantallasTrabajadores.MenuBotones
import com.example.tfg.Screens.PantallasTrabajadores.PantallasMesas.Mesa1
import com.example.tfg.Screens.PantallasTrabajadores.PantallasMesas.Mesa2
import com.example.tfg.Screens.PantallasTrabajadores.PantallasMesas.Mesa3
import com.example.tfg.Screens.PantallasTrabajadores.PantallasMesas.Mesa4
import com.example.tfg.Screens.PantallasTrabajadores.PantallasMesas.Mesa5
import com.example.tfg.Screens.PantallasTrabajadores.PantallasMesas.Mesa6
import com.example.tfg.Screens.PantallasTrabajadores.PantallasMesas.Mesa7
import com.example.tfg.Screens.PantallasTrabajadores.PantallasMesas.Mesa8
import com.example.tfg.Screens.PantallasTrabajadores.PantallasMesas.Mesa9
import com.example.tfg.Screens.PantallasTrabajadores.PantallasMesas.Mesas
import com.example.tfg.Screens.PantallasTrabajadores.Trabajadores.CartaTrabajadores.CartaBebidasTrabajadores
import com.example.tfg.Screens.PantallasTrabajadores.Trabajadores.CartaTrabajadores.CartaCervezasTrabajadores
import com.example.tfg.Screens.PantallasTrabajadores.Trabajadores.CartaTrabajadores.CartaTostasTrabajadores.CartaTostasTrabajadores
import com.example.tfg.Screens.PantallasTrabajadores.Trabajadores.CartaTrabajadores.CartaTostasTrabajadores.CartaTostasTrabajadores2
import com.example.tfg.Screens.PantallasTrabajadores.Trabajadores.CartaTrabajadores.CartaTostasTrabajadores.CartaTostasTrabajadores3
import com.example.tfg.Screens.PantallasTrabajadores.Trabajadores.CartaTrabajadores.CartaTostasTrabajadores.CartaTostasTrabajadores4
import com.example.tfg.Screens.PantallasTrabajadores.Trabajadores.CartaTrabajadores.CartaTostasTrabajadores.CartaTostasTrabajadores5
import com.example.tfg.Screens.PantallasTrabajadores.Trabajadores.Despensa
import com.example.tfg.Screens.Perfil
import com.example.tfg.navigation.AppScreens


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation() {
    val navigationController = rememberNavController()
    val context = LocalContext.current
    //val viewModel= NavHostController()
    //Declaramos en startDestination la pantalla en la que va a empezar.
    NavHost(navController = navigationController, startDestination = AppScreens.MenuPrimero.ruta)
    {
        composable(AppScreens.MenuPrimero.ruta) { MenuPrimero(navigationController) }
        /*Este es el menú de trabajadores*/composable(AppScreens.MenuBotones.ruta){ MenuBotones(navigationController) }
        composable(AppScreens.Despensa.ruta){ Despensa(navigationController) }
        composable(AppScreens.MESAS.ruta){ Mesas(navigationController) }
        composable(AppScreens.Mesa1.ruta){ Mesa1(navigationController) }
        composable(AppScreens.Mesa2.ruta){ Mesa2(navigationController) }
        composable(AppScreens.Mesa3.ruta){ Mesa3(navigationController) }
        composable(AppScreens.Mesa4.ruta){ Mesa4(navigationController) }
        composable(AppScreens.Mesa5.ruta){ Mesa5(navigationController) }
        composable(AppScreens.Mesa6.ruta){ Mesa6(navigationController) }
        composable(AppScreens.Mesa7.ruta){ Mesa7(navigationController) }
        composable(AppScreens.Mesa8.ruta){ Mesa8(navigationController) }
        composable(AppScreens.Mesa9.ruta){ Mesa9(navigationController) }
        composable(AppScreens.MenuClientes.ruta){MenuClientes(navigationController)}
       /* composable(AppScreens.pantallaLogin.ruta){pantallaLogin(navigationController) }*/

        composable(AppScreens.Perfil.ruta){ Perfil(navigationController, context)}
        composable(AppScreens.CartaTostasCliente.ruta){ CartaTostasCliente(navigationController) }
        composable(AppScreens.MenuCategorias.ruta){ MenuCategorias(navigationController)}
        composable(AppScreens.Reservas.ruta){ Reservas(navigationController)}
        composable(AppScreens.MenuCritica.ruta){ MenuCritica(navigationController) }

        composable(AppScreens.CrearCritica.ruta){ CrearCritica(navigationController, viewModel()) }
        composable(AppScreens.LoginScreen.ruta){ LoginScreen(navigationController) }
        composable(AppScreens.pantallaRegistro.ruta){
            pantallaRegistro(navigationController) }

        composable(AppScreens.ConsultarReserva.ruta){ConsultarReserva(navigationController)}
        composable(AppScreens.ConsultarReservaTrabajadores.ruta){ConsultarReservaTrabajadores(navigationController)}

        composable(AppScreens.CartaTostasTrabajadores.ruta){ CartaTostasTrabajadores(navigationController) }
        composable(AppScreens.CartaTostasTrabajadores2.ruta){ CartaTostasTrabajadores2(navigationController) }
        composable(AppScreens.CartaTostasTrabajadores3.ruta){ CartaTostasTrabajadores3(navigationController) }
        composable(AppScreens.CartaTostasTrabajadores4.ruta){ CartaTostasTrabajadores4(navigationController) }
        composable(AppScreens.CartaTostasTrabajadores5.ruta){ CartaTostasTrabajadores5(navigationController) }
        composable(AppScreens.CartaBebidasTrabajadores.ruta){ CartaBebidasTrabajadores(navigationController) }
        composable(AppScreens.CartaCervezasTrabajadores.ruta){ CartaCervezasTrabajadores(navigationController) }

        composable(AppScreens.MenuDelDia.ruta){ MenuDelDia(navigationController) }





        }

}








