package com.example.tfg.Screens.PantallasClientes

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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun pantallaLogin (navController: NavHostController) {
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


        var apellido by rememberSaveable { mutableStateOf("") }
        OutlinedTextField(
            value = apellido,
            onValueChange = {  if (it.length <= 10 && it.all { char -> char.isLetter() }) apellido = it },
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


        var correo by rememberSaveable { mutableStateOf("") }
        OutlinedTextField(
            value = correo,
            onValueChange = { nuevoValor ->
                if (nuevoValor.all { it.isLetterOrDigit() || it == '@' }) {
                    correo = nuevoValor
                }
            },
            singleLine = true,
            label = { Text("Introduzca su correo") }
        )


        var contrasena by rememberSaveable { mutableStateOf("") }
        OutlinedTextField(
            value = contrasena,
            onValueChange = { if (it.length <= 12) contrasena = it },
            singleLine = true,
            label = { Text("Introduzca su contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )


        Button(
            onClick = {
                // Lógica de inicio de sesión
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
