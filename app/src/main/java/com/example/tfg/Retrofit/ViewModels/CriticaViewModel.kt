package com.example.tfg.Retrofit.ViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfg.Retrofit.DataClases.Critica
import com.example.tfg.Retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CriticaViewModel : ViewModel(){

    var _listaCriticas: ArrayList<Critica> by mutableStateOf(arrayListOf())

    fun obtenerCriticas(){
        viewModelScope.launch(Dispatchers.IO){
            val response = RetrofitClient.webService.obtenerCriticas()
            withContext(Dispatchers.Main){
                if(response.body()!!.codigo == "200"){
                    _listaCriticas = response.body()?.data!!
                }
            }
        }
    }

    fun crearCritica(critica: Critica){
        viewModelScope.launch(Dispatchers.IO){
            try{
                val response = RetrofitClient.webService.crearCritica(critica)
                withContext(Dispatchers.Main){
                    if(response.body()!!.codigo == "200"){
                        obtenerCriticas()
                    }
                }
            }catch (e: Exception){
                // Manejar el error aquí
            }
        }
    }

    fun actualizarCritica(critica: Critica, idCritica: Int){
        val idCriticaString = idCritica.toString()
        viewModelScope.launch(Dispatchers.IO){
            try{
                val response = RetrofitClient.webService.actualizarCritica(idCriticaString, critica)
                withContext(Dispatchers.Main){
                    if(response.body()!!.codigo == "200"){
                        obtenerCriticas()
                    }
                }
            }catch (e: Exception){
                // Manejar el error aquí
            }
        }
    }


    fun eliminarCritica(idCritica: Int){
        val idCriticaString = idCritica.toString()
        viewModelScope.launch(Dispatchers.IO){
            try{
                val response = RetrofitClient.webService.eliminarCritica(idCriticaString)
                withContext(Dispatchers.Main){
                    if(response.body()!!.codigo == "200"){
                        obtenerCriticas()
                    }
                }
            }catch (e: Exception){
                // Manejar el error aquí
            }
        }
    }


}

