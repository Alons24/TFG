package com.example.tfg.Retrofit.ViewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfg.Retrofit.WebService
import kotlinx.coroutines.launch

class UserViewModel (private val api: WebService, private val context: Context) : ViewModel() {

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = api.signUp(WebService.Request(email, password))
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

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = api.signIn(WebService.Request(email, password))
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
}