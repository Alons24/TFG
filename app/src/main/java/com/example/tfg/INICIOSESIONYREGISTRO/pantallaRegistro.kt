package com.example.tfg.INICIOSESIONYREGISTRO



import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.tfg.R
import com.example.tfg.Retrofit.DataClases.User
import com.example.tfg.Retrofit.RetrofitClient
import com.example.tfg.Retrofit.ViewModels.UserViewModel
import com.example.tfg.ui.theme.Black
import com.example.tfg.ui.theme.BlueGray
import com.example.tfg.ui.theme.focusedTextFieldText
import com.example.tfg.ui.theme.textFieldContainer
import com.example.tfg.ui.theme.unfocusedTextFieldText
import kotlin.random.Random
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay

@Composable
fun pantallaRegistro(navController: NavHostController){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var password2 by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var rol by remember { mutableStateOf("2") }
    var idUsuario by remember { mutableStateOf(Random.nextInt(1000, 9999)) }
    val context = LocalContext.current
    val api = RetrofitClient.webService
    val factory = UserViewModel.UserViewModelFactory(api, context)
    val viewModel: UserViewModel = viewModel(factory = factory)
    var rememberMe by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    val uiColor = if (isSystemInDarkTheme()) Color.White else Black
    var mensajeConfirmacion by remember { mutableStateOf("") }
    var showRegistrationCompletedDialog by remember { mutableStateOf(false) }


    Surface {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

            TopSectionRegistro(mensajeConfirmacion)


                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    singleLine = true,
                    label = {
                        Text(
                            text = "Nombre",
                            style = MaterialTheme.typography.labelMedium,
                            color = uiColor
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        unfocusedPlaceholderColor = MaterialTheme.colorScheme.unfocusedTextFieldText,
                        focusedPlaceholderColor = MaterialTheme.colorScheme.focusedTextFieldText,
                        unfocusedContainerColor = MaterialTheme.colorScheme.textFieldContainer,
                        focusedContainerColor = MaterialTheme.colorScheme.textFieldContainer,
                    ),
                    trailingIcon = {
                        androidx.compose.material3.TextButton(onClick = { /*TODO*/ }) {
                            Text(
                                text = "",
                                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium),
                                color = uiColor
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = apellidos,
                    onValueChange = { apellidos = it },
                    singleLine = true,
                    label = {
                        Text(
                            text = "Apellidos",
                            style = MaterialTheme.typography.labelMedium,
                            color = uiColor
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        unfocusedPlaceholderColor = MaterialTheme.colorScheme.unfocusedTextFieldText,
                        focusedPlaceholderColor = MaterialTheme.colorScheme.focusedTextFieldText,
                        unfocusedContainerColor = MaterialTheme.colorScheme.textFieldContainer,
                        focusedContainerColor = MaterialTheme.colorScheme.textFieldContainer,
                    ),
                    trailingIcon = {
                        androidx.compose.material3.TextButton(onClick = { /*TODO*/ }) {
                            Text(
                                text = "",
                                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium),
                                color = uiColor
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = telefono,
                    onValueChange = { telefono = it },
                    singleLine = true,
                    label = {
                        Text(
                            text = "Teléfono",
                            style = MaterialTheme.typography.labelMedium,
                            color = uiColor
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        unfocusedPlaceholderColor = MaterialTheme.colorScheme.unfocusedTextFieldText,
                        focusedPlaceholderColor = MaterialTheme.colorScheme.focusedTextFieldText,
                        unfocusedContainerColor = MaterialTheme.colorScheme.textFieldContainer,
                        focusedContainerColor = MaterialTheme.colorScheme.textFieldContainer,
                    ),
                    trailingIcon = {
                        androidx.compose.material3.TextButton(onClick = { /*TODO*/ }) {
                            Text(
                                text = "",
                                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium),
                                color = uiColor
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = email,
                    onValueChange = { email = it },
                    singleLine = true,
                    label = {
                        Text(
                            text = "Correo electrónico",
                            style = MaterialTheme.typography.labelMedium,
                            color = uiColor
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        unfocusedPlaceholderColor = MaterialTheme.colorScheme.unfocusedTextFieldText,
                        focusedPlaceholderColor = MaterialTheme.colorScheme.focusedTextFieldText,
                        unfocusedContainerColor = MaterialTheme.colorScheme.textFieldContainer,
                        focusedContainerColor = MaterialTheme.colorScheme.textFieldContainer,
                    ),
                    trailingIcon = {
                        androidx.compose.material3.TextButton(onClick = { /*TODO*/ }) {
                            Text(
                                text = "",
                                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium),
                                color = uiColor
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                var passwordVisibility by remember { mutableStateOf(false) }

                OutlinedTextField(

                    value = password,
                    onValueChange = { password = it },
                    singleLine = true,
                    visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                    label = {
                        Text(
                            text = "Contraseña",
                            style = MaterialTheme.typography.labelMedium,
                            color = uiColor,

                        )
                    },

                    colors = TextFieldDefaults.colors(
                        unfocusedPlaceholderColor = MaterialTheme.colorScheme.unfocusedTextFieldText,
                        focusedPlaceholderColor = MaterialTheme.colorScheme.focusedTextFieldText,
                        unfocusedContainerColor = MaterialTheme.colorScheme.textFieldContainer,
                        focusedContainerColor = MaterialTheme.colorScheme.textFieldContainer,
                    ),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                            Icon(
                                imageVector = if (passwordVisibility) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                                contentDescription = if (passwordVisibility) "Ocultar contraseña" else "Mostrar contraseña"
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(15.dp))

                Box(modifier = Modifier.fillMaxSize()) {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp), // Ajustar la altura aquí
                        onClick = {
                            if (nombre.isNotEmpty() && apellidos.isNotEmpty() && password.isNotEmpty() &&
                        telefono.isNotEmpty() && email.isNotEmpty() && email.contains('@')
                    ) {
                        if (telefono.length != 9) {
                            mensajeConfirmacion = "El teléfono debe tener exactamente 9 números"
                        } else if (!email.contains('@')) {
                            mensajeConfirmacion = "El correo no es váido"
                        } else if (!password.matches(Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$"))) {
                            mensajeConfirmacion = "La contraseña debe contener al menos una letra mayúscula, una letra minúscula y un número"
                        } else if (nombre.length > 20) {
                            mensajeConfirmacion = "El nombre debe tener menos de 20 caracteres"
                        } else if (apellidos.length > 20) {
                            mensajeConfirmacion = "Los apellidos deben tener menos de 20 caracteres"
                        } else {
                            val user = User(idUsuario, nombre, apellidos, email, password, telefono, rol)
                            viewModel.signUp(user.email, user.password, user.nombre, user.apellidos, user.telefono, user.rol, user.idUsuario)
                            showDialog = true
                            showRegistrationCompletedDialog = true
                        }
                    } else {
                        mensajeConfirmacion = "Por favor, completa todos los campos" // Mensaje de error si falta algún campo
                    }

                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isSystemInDarkTheme()) BlueGray else Black,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(size = 4.dp)
                    ) {
                        Text(
                            text = "Registrarse",
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium)
                        )
                    }
                }
            if (showRegistrationCompletedDialog) {
                AlertDialog(
                    onDismissRequest = { showRegistrationCompletedDialog = false },
                    title = { Text(text = "¡REGISTRO COMPLETADO!") },
                    confirmButton = { },
                    dismissButton = { }
                )
            }

            LaunchedEffect(showRegistrationCompletedDialog) {
                if (showRegistrationCompletedDialog) {
                    delay(3000L) // Espera 3 segundos
                    navController.navigate("menuClientes") // Navega a menuClientes
                }
            }
        }
    }
}

@Composable
fun TopSectionRegistro(mensajeConfirmacion: String) {
    val uiColor = if (isSystemInDarkTheme()) Color.White else Black

    Box(
        modifier = Modifier.padding(top = 0.dp), // Asegurarse de que no haya padding superior
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.4f) // Ajustar la altura aquí
                .padding(bottom = 5.dp), // Reducir el espaciado aquí

            painter = painterResource(id = R.drawable.shape),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )

        Row(
            modifier = Modifier.padding(top = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(42.dp),
                painter = painterResource(id = R.drawable.logo_cerveceria_montemayor),
                contentDescription = stringResource(id = R.string.app_logo),
                tint = uiColor
            )
            Spacer(modifier = Modifier.width(15.dp))
            Column {
                Text(
                    text = stringResource(id = R.string.the_tolet),
                    style = MaterialTheme.typography.headlineMedium,
                    color = uiColor
                )
                Text(
                    text = stringResource(id = R.string.find_house),
                    style = MaterialTheme.typography.titleMedium,
                    color = uiColor
                )
            }
        }

        Text(
            modifier = Modifier
                .padding(bottom = 50.dp) // Reducir el espaciado aquí
                .align(alignment = Alignment.BottomCenter),
            text = stringResource(id = R.string.registro),
            style = MaterialTheme.typography.headlineLarge,
            color = uiColor
        )

        // Agregar este componente Text para mostrar mensajeConfirmacion
        Text(
            modifier = Modifier
                .padding(bottom = 20.dp) // Reducir el espaciado aquí
                .align(alignment = Alignment.BottomCenter),
            text = mensajeConfirmacion,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Red // Puedes cambiar el color según tus necesidades
        )
    }
}
