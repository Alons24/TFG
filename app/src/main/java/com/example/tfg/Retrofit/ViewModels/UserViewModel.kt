package com.example.tfg.Retrofit.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfg.Retrofit.WebService
import kotlinx.coroutines.launch
import com.example.tfg.Retrofit.Response.UserResponse

class UserViewModel (private val api: WebService) : ViewModel(){

    fun signUp(correo: String, password: String) {
        viewModelScope.launch {
            try {
                val response = api.signUp(WebService.Request(correo, password))
                // Guarda el token de respuesta, maneja el éxito del registro
            } catch (e: Exception) {
                // Maneja el error
            }
        }
    }

    fun signIn(correo: String, password: String): Boolean {
    var loginSuccessful = false
    viewModelScope.launch {
        try {
            val response = api.signIn(WebService.Request(correo, password))
            // Si las credenciales son correctas, establece loginSuccessful en true
            loginSuccessful = response.body()!!.codigo == "200" // 200 es el código de éxito
        } catch (e: Exception) {
            // Maneja el error
        }
    }
    return loginSuccessful
}

}