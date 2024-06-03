package com.example.tfg.Screens.PantallasClientes.Reservas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.tfg.Retrofit.DataClases.Reserva
import com.example.tfg.Retrofit.SessionManager
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun ConsultarReserva(navController: NavHostController) {
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

    ReservasScreen(navController, reservas)
}

@Composable
fun ReservasScreen(navController: NavHostController, reservas: List<Reserva>) {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        items(reservas) { reserva ->
            ReservaItem(reserva)
        }
    }
}

@Composable
fun ReservaItem(reserva: Reserva) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White,
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
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

@Preview(showBackground = true)
@Composable
fun PreviewReservaItem() {
    ReservaItem(
        reserva = Reserva(
            idReserva = "123",
            nombreCliente = "Juan",
            fechaReserva = "2023-12-31"
            // Añade los demás campos según sea necesario
        )
    )
}