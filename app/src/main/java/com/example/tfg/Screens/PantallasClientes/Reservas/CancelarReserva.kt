package com.example.tfg.Screens.PantallasClientes.Reservas

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.tfg.Retrofit.DataClases.Reserva
import com.example.tfg.Retrofit.SessionManager
import com.example.tfg.Retrofit.ViewModels.ReservasViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken



@Composable
fun CancelarReserva(navController: NavHostController, viewModel: ReservasViewModel) {

    val userEmail = SessionManager.getEmail(LocalContext.current)
    val firestore = FirebaseFirestore.getInstance()

    var reservas by remember { mutableStateOf<List<Reserva>>(emptyList()) }

    LaunchedEffect(key1 = userEmail) {
        firestore.collection("Reserva")
            .whereEqualTo("emailCliente", userEmail)
            .get()
            .addOnSuccessListener { documents ->
                val reservasList = mutableListOf<Reserva>()
                for (document in documents) {
                    reservasList.add(document.toObject(Reserva::class.java))
                }
                reservas = reservasList
            }
            .addOnFailureListener { exception ->
                // Handle any errors here
            }
    }

    CancelarReservasScreen(viewModel, navController, reservas)
}

@Composable
fun CancelarReservasScreen(viewModel: ReservasViewModel, navController: NavHostController, reservas: List<Reserva>) {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        items(reservas) { reserva ->
            CancelarReservaItem(reserva, viewModel, navController)
        }
    }
}

@Composable
fun CancelarReservaItem(
    reserva: Reserva,
    viewModel: ReservasViewModel,
    navController: NavHostController,
) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Confirmación de cancelación") },
            text = { Text("¿Estás seguro de que quieres cancelar esta reserva?") },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.deleteReserva(reserva.idReserva)
                    try {
                        showDialog = false
                        navController.popBackStack()
                    } catch (e: Exception) {
                        Log.e("Error", "Ha ocurrido un error después de eliminar la reserva: ${e.message}")
                    }
                }) {
                    Text("Sí")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("No")
                }
            }
        )
    }
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White,
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable { showDialog = true }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "ID de reserva: ${reserva.idReserva}", style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Nombre del cliente: ${reserva.nombreCliente}", style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Fecha de reserva: ${reserva.fechaReserva}", style = MaterialTheme.typography.body2)
            // Agrega más campos según sea necesario
        }
    }
}
