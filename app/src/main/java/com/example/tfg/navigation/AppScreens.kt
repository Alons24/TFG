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
    object Mesa3:AppScreens("Mesa3")
    object Mesa4:AppScreens("Mesa4")
    object Mesa5:AppScreens("Mesa5")
    object Mesa6:AppScreens("Mesa6")
    object Mesa7:AppScreens("Mesa7")
    object Mesa8:AppScreens("Mesa8")
    object Mesa9:AppScreens("Mesa9")


    object MenuClientes:AppScreens("MenuClientes")
    object DejarResena:AppScreens("DejarResena")
    object InicioSesion:AppScreens("InicioSesion")
    object pantallaLogin:AppScreens("pantallaLogin")

    object Perfil:AppScreens("Perfil")

    object Carta: AppScreens("CartaEntrantes")

    object MenuCategorias:AppScreens("MenuCategorias")

    object Reservas:AppScreens("Rerservas")
    object Reservar:AppScreens("Reservar")



}
