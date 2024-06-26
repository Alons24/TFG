package com.example.tfg.INICIOSESIONYREGISTRO

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.tfg.Retrofit.DataClases.User
import com.example.tfg.Retrofit.RetrofitClient
import com.example.tfg.Retrofit.ViewModels.UserViewModel
import kotlin.random.Random
/*
@Composable
fun pantallaLogin (navController: NavHostController) {
    val context = LocalContext.current
    val api = RetrofitClient.webService
    val factory = UserViewModel.UserViewModelFactory(api, context)
    val viewModel: UserViewModel = viewModel(factory = factory)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "REGÍSTRATE", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(20.dp))
        Divider()


        var nombre by rememberSaveable { mutableStateOf("") }
        OutlinedTextField(
            value = nombre,
            onValueChange = { if (it.length <= 10 && it.all { char -> char.isLetter() }) nombre = it },
            singleLine = true,
            label = {
                Text("Introduzca su nombre")
            }
        )


        var apellidos by rememberSaveable { mutableStateOf("") }
        OutlinedTextField(
            value = apellidos,
            onValueChange = {  if (it.length <= 10 && it.all { char -> char.isLetter() }) apellidos = it },
            singleLine = true,
            label = {
                Text("Introduzca su apellido")
            }
        )


        var telefono by rememberSaveable { mutableStateOf("") }
        OutlinedTextField(
            value = telefono,
            onValueChange = { nuevoValor ->
                if (nuevoValor.all { it.isDigit() } && nuevoValor.length <= 17) {
                    telefono = nuevoValor
                }
            },
            singleLine = true,
            label = { Text("Introduzca su teléfono") }
        )


        var email by rememberSaveable { mutableStateOf("") }
        OutlinedTextField(
            value = email,
            onValueChange = { nuevoValor ->
                if (nuevoValor.all { it.isLetterOrDigit() || it == '@' }) {
                    email = nuevoValor
                }
            },
            singleLine = true,
            label = { Text("Introduzca su correo") }
        )


        var password by rememberSaveable { mutableStateOf("") }
        OutlinedTextField(
            value = password,
            onValueChange = { if (it.length <= 12) password = it },
            singleLine = true,
            label = { Text("Introduzca su contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )


        var rol by remember { mutableStateOf("2") } //Siempre sera 2 para el cliente
        var idUsuario by remember { mutableStateOf(Random.nextInt(1, 1000000000)) }

        var showDialog by remember { mutableStateOf(false) }
        var mensajeConfirmacion by remember { mutableStateOf("") }
        Button(
            onClick = {
                if (nombre.isNotEmpty() && apellidos.isNotEmpty() && password.isNotEmpty() &&
                    telefono.isNotEmpty() && email.isNotEmpty() && email.contains('@')
                ) {
                    val user = User(idUsuario, nombre, apellidos, email, password, telefono, rol)
                    viewModel.signUp(user.email, user.password)
                    showDialog = true
                } else {
                    mensajeConfirmacion =
                        "Por favor, completa todos los campos" // Mensaje de error si falta algún campo
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(66.dp, 10.dp, 66.dp, 0.dp),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(Color(59, 64, 72, 255)),
        ) {
            Text(
                text = "REGISTRARSE",
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }
}

*/
