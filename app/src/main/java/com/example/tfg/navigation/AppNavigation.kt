import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController

import com.example.tfg.Screens.Despensa
import com.example.tfg.Screens.MenuBotones



import com.example.tfg.Screens.MenuPrimero
import com.example.tfg.Screens.PantallasMesas.Mesa

import com.example.tfg.navigation.AppScreens


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun AppNavigation() {
    val navigationController = rememberNavController()
    //Declaramos en startDestination la pantalla en la que va a empezar.
    NavHost(navController = navigationController, startDestination = AppScreens.MenuPrimero.ruta)
    {
        composable(AppScreens.MenuPrimero.ruta) { MenuPrimero(navigationController) }
        composable(AppScreens.MenuBotones.ruta){MenuBotones(navigationController)}
        composable(AppScreens.Despensa.ruta){ Despensa(navigationController) }
        composable(AppScreens.MESAS.ruta){Mesa(navigationController)}

        }

}


