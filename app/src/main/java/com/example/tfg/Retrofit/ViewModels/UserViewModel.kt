package com.example.tfg.Retrofit.ViewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.tfg.Retrofit.WebService
import kotlinx.coroutines.launch


class UserViewModel (private val api: WebService, private val context: Context) : ViewModel() {



    fun signUp(email: String, password: String, nombre: String, apellidos: String, telefono: String, rol: String, idUsuario: Int) {
        viewModelScope.launch {
            try {
                val response = api.signUp(WebService.Request(email, password, nombre, apellidos, telefono, rol,
                    idUsuario.toString()
                ))
                if (response.isSuccessful && response.body() != null) {
                    val sharedPref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE)
                    with (sharedPref.edit()) {
                        putString("token", response.body()!!.token)
                        apply()
                    }
                } else {
                    // Maneja el error
                }
            } catch (e: Exception) {
                // Maneja el error
            }
        }
    }

    fun signIn(email: String, password: String, nombre: String, apellidos: String, telefono: String, rol: String, idUsuario: Int) {
        viewModelScope.launch {
            try {
                val response = api.signIn(WebService.Request(email, password, nombre, apellidos, telefono, rol,
                    idUsuario.toString()
                ))
                if (response.isSuccessful && response.body() != null) {
                    val sharedPref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE)
                    with (sharedPref.edit()) {
                        putString("token", response.body()!!.token)
                        apply()
                    }
                } else {
                    // Maneja el error
                }
            } catch (e: Exception) {
                // Maneja el error
            }
        }
    }



    class UserViewModelFactory(private val api: WebService, private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return UserViewModel(api, context) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}


