package com.example.tfg.navigation



// Definición de una clase sellada llamada AppScreens con una propiedad ruta
sealed class AppScreens(val ruta: String) {

    object MenuPrimero : AppScreens("MenuPrimero")

    object Despensa: AppScreens("Despensa")

    object InicioSesion: AppScreens("InicioSesion")

    object MenuGeneral: AppScreens("MenuGeneral")

    object TrabajadoresInforme: AppScreens("TrabajadoresInforme")


}
