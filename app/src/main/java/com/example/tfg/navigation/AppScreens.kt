package com.example.tfg.navigation



// Definición de una clase sellada llamada AppScreens con una propiedad ruta
sealed class AppScreens(val ruta: String) {

    object MenuBotones: AppScreens("MenuBotones")
    object AnadirTrabajador:AppScreens("AnadirProducto")

    //RUTAS MESAS
    object MESAS: AppScreens("MESAS")
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

    //RUTAS INICIO SESION Y MENU PRINCIPAL

    object pantallaLogin:AppScreens("pantallaLogin")
    object MenuPrimero : AppScreens("MenuPrimero")
    object LoginScreen:AppScreens("LoginScreen")
    object pantallaRegistro:AppScreens("pantallaRegistro")


    object Perfil:AppScreens("Perfil")

    //RUTAS CARTA Y MENU CATEGORIAS
    object CartaEntrantes: AppScreens("CartaEntrantes")
    object MenuCategorias:AppScreens("MenuCategorias")
    object Despensa: AppScreens("Despensa")

    //RUTAS RESERVAS
    object Reservas:AppScreens("Reservas")
    object Reservar:AppScreens("Reservar")
    object ConsultarReserva:AppScreens("ConsultarReserva")
    object CancelarReserva:AppScreens("CancelarReserva")
    object ModificarReserva:AppScreens("ModificarReserva")


    //RUTAS CRITICAS
    object MenuCritica:AppScreens("MenuCritica")
    object CrearCritica:AppScreens("CrearCritica")
    object ActualizarCritica:AppScreens("ActualizarCritica")
    object EliminarCritica:AppScreens("EliminarCritica")
    object ConsultarCritica:AppScreens("ConsultarCritica")

    object CartaTostasTrabajadores:AppScreens("CartaTostasTrabajadores")

}
