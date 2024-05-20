package com.example.tfg.navigation



// Definición de una clase sellada llamada AppScreens con una propiedad ruta
sealed class AppScreens(val ruta: String) {


    object MenuPrimero : AppScreens("MenuPrimero")
    object MenuBotones: AppScreens("MenuBotones")
    object Despensa: AppScreens("Despensa")
    object MESAS: AppScreens("MESAS")
    object AnadirTrabajador:AppScreens("AnadirProducto")
    object Mesa1:AppScreens("Mesa1")
    object Mesa2:AppScreens("Mesa2")
    object MenuClientes:AppScreens("MenuClientes")
    object DejarResena:AppScreens("DejarResena")
    object InicioSesion:AppScreens("InicioSesion")
    object pantallaLogin:AppScreens("pantallaLogin")

    object Perfil:AppScreens("Perfil")

    object Carta: AppScreens("Carta")



}
