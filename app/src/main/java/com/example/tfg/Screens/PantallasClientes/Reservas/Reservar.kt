package com.example.tfg.Screens.PantallasClientes.Reservas

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tfg.Retrofit.DataClases.Reserva
import com.example.tfg.Retrofit.SessionManager
import com.example.tfg.Retrofit.ViewModels.ReservasViewModel
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Reservar(navController: NavHostController, viewModel: ReservasViewModel) {
    var fechaReserva by remember { mutableStateOf("") }
    var horaReserva by remember { mutableStateOf("") }
    var numComensales by remember { mutableStateOf(0) }
    var nombreCliente by remember { mutableStateOf("") }
    var telefonoCliente by remember { mutableStateOf("") }
    var emailCliente by remember { mutableStateOf("") }
    var observaciones by remember { mutableStateOf("") }
    val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val context = LocalContext.current
    val db = FirebaseFirestore.getInstance()
    val coleccion = db.collection("Reserva")
    val nuevoDocumento = coleccion.document()
    val idReserva = nuevoDocumento.id
    val userEmail = SessionManager.getEmail(context) // Reemplaza esto con tu propio método para obtener el correo electrónico del usuario
    SessionManager.setEmail(context, userEmail)

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Navigate back")
                    }
                },
                title = { Text("Reservar Mesa") }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(Color(0xFFF5F5F5))
            ) {
                Text(
                    text = "Reservar Mesa",
                    style = TextStyle(fontSize = 24.sp, color = Color.Black),
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                OutlinedTextField(
                    value = fechaReserva,
                    onValueChange = { },
                    label = { Text("Fecha de reserva", color = Color.Gray) },
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                        .clickable {
                            val calendar = Calendar.getInstance()
                            val year = calendar.get(Calendar.YEAR)
                            val month = calendar.get(Calendar.MONTH)
                            val day = calendar.get(Calendar.DAY_OF_MONTH)
                            DatePickerDialog(context, { _, selectedYear, selectedMonth, selectedDay ->
                                fechaReserva = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                            }, year, month, day).show()
                        },
                    textStyle = TextStyle(color = Color.Black, fontSize = 16.sp)
                )

                OutlinedTextField(
                    value = horaReserva,
                    onValueChange = { },
                    label = { Text("Hora de reserva", color = Color.Gray) },
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                        .clickable {
                            val calendar = Calendar.getInstance()
                            val hour = calendar.get(Calendar.HOUR_OF_DAY)
                            val minute = calendar.get(Calendar.MINUTE)
                            TimePickerDialog(context, { _, selectedHour, selectedMinute ->
                                horaReserva = "$selectedHour:$selectedMinute"
                            }, hour, minute, true).show()
                        },
                    textStyle = TextStyle(color = Color.Black, fontSize = 16.sp)
                )

                OutlinedTextField(
                    value = numComensales.toString(),
                    onValueChange = { it.toIntOrNull()?.let { num -> numComensales = num } },
                    label = { Text("Número de comensales", color = Color.Gray) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )

                OutlinedTextField(
                    value = nombreCliente,
                    onValueChange = { nombreCliente = it },
                    label = { Text("Nombre del cliente", color = Color.Gray) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    textStyle = TextStyle(color = Color.Black, fontSize = 16.sp)
                )

                OutlinedTextField(
                    value = telefonoCliente,
                    onValueChange = { telefonoCliente = it },
                    label = { Text("Teléfono del cliente", color = Color.Gray) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone)
                )

                OutlinedTextField(
                    value = emailCliente,
                    onValueChange = { emailCliente = it },
                    label = { Text("Email del cliente", color = Color.Gray) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
                )

                OutlinedTextField(
                    value = observaciones,
                    onValueChange = { observaciones = it },
                    label = { Text("Observaciones", color = Color.Gray) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    textStyle = TextStyle(color = Color.Black, fontSize = 16.sp)
                )

                Button(
                    onClick = {
                        if (fechaReserva.isEmpty() || horaReserva.isEmpty() || numComensales == 0 ||
                            nombreCliente.isEmpty() || telefonoCliente.isEmpty() || emailCliente.isEmpty()) {
                            Toast.makeText(context, "Por favor, complete todos los campos obligatorios.", Toast.LENGTH_SHORT).show()
                        } else {
                            var userEmail = SessionManager.getEmail(context)
                            val date = if (fechaReserva.isNotEmpty()) format.parse(fechaReserva) else null
                            val reserva = date?.let { it1 ->
                                Reserva(idReserva,
                                    numComensales,
                                    it1.toString(), horaReserva, nombreCliente, telefonoCliente, emailCliente, observaciones)
                            }
                            if (reserva != null) {
                                viewModel.createReserva(reserva)
                            }
                            val reservaMap = hashMapOf(
                                "idReserva" to idReserva,
                                "numComensales" to numComensales,
                                "fechaReserva" to fechaReserva,
                                "horaReserva" to horaReserva,
                                "nombreCliente" to nombreCliente,
                                "telefonoCliente" to telefonoCliente,
                                "emailCliente" to emailCliente,
                                "observaciones" to observaciones
                            )
                            nuevoDocumento.set(reservaMap)
                            userEmail = SessionManager.getEmail(context)
                            val reservas = coleccion.whereEqualTo("email", userEmail).get()
                            Toast.makeText(context, "Reserva realizada con éxito.", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        }

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    colors = ButtonDefaults.buttonColors(Color.Blue),
                ) {
                    Text("Reservar", color = Color.White)
                }
            }
        }
    )
}