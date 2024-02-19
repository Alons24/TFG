package com.example.tfg.navigation



// Definición de una clase sellada llamada AppScreens con una propiedad ruta
sealed class AppScreens(val ruta: String) {


    object MenuPrimero : AppScreens("MenuPrimero")
    object MenuBotones: AppScreens("MenuBotones")
    object Despensa: AppScreens("Despensa")
    object MESAS: AppScreens("MESAS")
    object AnadirProducto:AppScreens("AnadirProducto")
    object InicioSesion: AppScreens("InicioSesion")



}
