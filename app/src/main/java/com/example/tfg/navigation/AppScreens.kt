package com.example.tfg.navigation



// Definición de una clase sellada llamada AppScreens con una propiedad ruta
sealed class AppScreens(val ruta: String) {


    object MenuPrimero : AppScreens("MenuPrimero")
    object MenuBotones: AppScreens("MenuBotones")
    object Despensa: AppScreens("Despensa")
    object MESAS: AppScreens("MESAS")
    object AnadirProducto:AppScreens("AnadirProducto")
    object Mesa1:AppScreens("Mesa1")
    object Mesa2:AppScreens("Mesa2")

    //Falta crear el onjeto de inicio de Sesion en El AppScreens y en AppNavigation.


}
