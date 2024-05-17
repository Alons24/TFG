package com.example.tfg.Retrofit.ViewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfg.Retrofit.DataClases.Reserva
import com.example.tfg.Retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReservasViewModel: ViewModel() {

    var _listaReservas: ArrayList<Reserva> by mutableStateOf(arrayListOf())

    fun getReservas(){
        viewModelScope.launch(Dispatchers.IO){
            val response = RetrofitClient.webService.getReservas()
            withContext(Dispatchers.Main){
                if(response.body()!!.codigo == "200"){
                    _listaReservas = response.body()?.data!!
                }
            }
        }
    }

    fun createReserva(reserva: Reserva){
        viewModelScope.launch(Dispatchers.IO){
            try{
            val response = RetrofitClient.webService.createReserva(reserva)
            withContext(Dispatchers.Main){
                if(response.body()!!.codigo == "200"){
                    getReservas()
                }
            }
        }catch (e: Exception){
            Log.e("Error", "Error al crear la reserva: ${e.message}")
        }
        }
    }

    fun updateReseerva(reserva: Reserva, idReserva: Int){
        val idReservaString = idReserva.toString()
        viewModelScope.launch(Dispatchers.IO){
            try{
            val response = RetrofitClient.webService.updateReserva(idReserva, reserva)
            withContext(Dispatchers.Main){
                if(response.body()!!.codigo == "200"){
                    getReservas()
                }
            }
        }catch (e: Exception){
            Log.e("Error", "Error al actualizar la reserva: ${e.message}")
            }
        }
    }

    fun deleteReserva(idReserva: String){
        viewModelScope.launch(Dispatchers.IO){
            try{
            val response = RetrofitClient.webService.deleteReserva(idReserva)
            withContext(Dispatchers.Main){
                if(response.body()!!.codigo == "200"){
                    getReservas()
                }
            }
        }catch (e: Exception){
            Log.e("Error", "Error al eliminar la reserva: ${e.message}")
            }
        }
    }
}