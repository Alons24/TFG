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
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.tfg.Retrofit.DataClases.Reserva
import com.example.tfg.Retrofit.SessionManager
import com.example.tfg.Retrofit.ViewModels.ReservasViewModel
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun ModificarReserva(navController: NavHostController, viewModel: ReservasViewModel) {

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

    ModificarReservasScreen(viewModel, navController, reservas)
}

@Composable
fun ModificarReservasScreen(viewModel: ReservasViewModel, navController: NavHostController, reservas: List<Reserva>) {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        items(reservas) { reserva ->
            ModificarReservaItem(reserva, viewModel, navController)
        }
    }
}

@Composable
fun ModificarReservaForm(reserva: Reserva?, onReservaModified: (Reserva) -> Unit) {
    if (reserva != null) {
        var idReserva by remember { mutableStateOf(reserva.idReserva) }
        var nombreCliente by remember { mutableStateOf(reserva.nombreCliente) }
        var fechaReserva by remember { mutableStateOf(reserva.fechaReserva) }
        var horaReserva by remember { mutableStateOf(reserva.horaReserva) }
        var emailCliente by remember { mutableStateOf(reserva.emailCliente) }
        var telefonoCliente by remember { mutableStateOf(reserva.telefonoCliente) }
        var numComensales by remember { mutableStateOf(reserva.numComensales) }


        Column {
            Text("ID de reserva")
            TextField(value = idReserva, onValueChange = { idReserva = it })

            Text("Nombre del cliente")
            TextField(value = nombreCliente, onValueChange = { nombreCliente = it })

            Text("Fecha de reserva")
            TextField(value = fechaReserva, onValueChange = { fechaReserva = it })

            Text("Hora de reserva")
            TextField(value = horaReserva, onValueChange = { horaReserva = it })




            Text("Teléfono del cliente")
            TextField(value = telefonoCliente, onValueChange = { telefonoCliente = it })

            Text("Número de comensales")
            TextField(
                value = numComensales.toString(),
                onValueChange = { newValue ->
                    numComensales = newValue.toIntOrNull() ?: 0
                }
            )

            TextField(
                value = emailCliente,
                onValueChange = { emailCliente = it },
                modifier = Modifier.alpha(0f)
            )

            Button(onClick = {
                val modifiedReserva = Reserva(
                    idReserva = idReserva,
                    nombreCliente = nombreCliente,
                    fechaReserva = fechaReserva,
                    horaReserva = horaReserva,
                    emailCliente = emailCliente,
                    telefonoCliente = telefonoCliente,
                    numComensales = numComensales
                )
                onReservaModified(modifiedReserva)
            }) {
                Text("Modificar Reserva")
            }
        }
    } else {
        Text("La reserva no existe o no se pudo recuperar.")
    }
}

@Composable
fun ModificarReservaItem(
    reserva: Reserva,
    viewModel: ReservasViewModel,
    navController: NavHostController,
) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            ModificarReservaForm(reserva) { modifiedReserva ->
                viewModel.updateReserva(modifiedReserva, modifiedReserva.idReserva)
                try {
                    showDialog = false
                    navController.popBackStack()
                } catch (e: Exception) {
                    Log.e("Error", "Ha ocurrido un error después de modificar la reserva: ${e.message}")
                }
            }
        }
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