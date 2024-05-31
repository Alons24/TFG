package com.example.tfg.INICIOSESIONYREGISTRO

import android.content.Context
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.tfg.R
import com.example.tfg.Retrofit.RetrofitClient
import com.example.tfg.Retrofit.ViewModels.UserViewModel
import com.loc.composeloginscreen.ui.theme.Black
import com.loc.composeloginscreen.ui.theme.Roboto
import com.loc.composeloginscreen.ui.theme.focusedTextFieldText
import com.loc.composeloginscreen.ui.theme.textFieldContainer
import com.loc.composeloginscreen.ui.theme.unfocusedTextFieldText
import androidx.compose.material3.TextFieldDefaults
import com.example.tfg.Retrofit.DataClases.User
import com.loc.composeloginscreen.ui.theme.BlueGray
import kotlin.random.Random

@Composable
fun pantallaRegistro(navController: NavHostController){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var password2 by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var rol by remember { mutableStateOf("") }
    var idUsuario by remember { mutableStateOf("") }
    val context = LocalContext.current
    val api = RetrofitClient.webService
    val factory = UserViewModel.UserViewModelFactory(api, context)
    val viewModel: UserViewModel = viewModel(factory = factory)
    var rememberMe by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    Surface {
        Column(modifier = Modifier.fillMaxSize()) {
            TopSectionRegistro()
            Spacer(modifier = Modifier.height(36.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp)
            ) {
                SiginSection(
                    email = email,
                    password = password,
                    password2 = password2,
                    nombre = nombre,
                    apellidos = apellidos,
                    telefono = telefono,
                    rol = rol,
                    idUsuario = idUsuario,
                    viewModel = viewModel,
                    rememberMe = rememberMe,
                    showDialog = showDialog,
                    navController = navController,
                    )



                val uiColor = if (isSystemInDarkTheme()) Color.White else Black
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontFamily = Roboto)) {
                            append("¿Ya tienes una cuenta? ")
                        }
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontFamily = Roboto)) {
                            append("Inicia sesión")
                        }
                    },
                    fontSize = 16.sp,
                    color = uiColor,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 20.dp)
                )

            }
        }

    }
}

@Composable
fun SiginSection(
    email: String,
    password: String,
    password2: String,
    nombre: String,
    apellidos: String,
    telefono: String,
    rol: String = "2",
    idUsuario: String = Random.nextInt(1, 1000000000).toString(),
    viewModel: UserViewModel,
    rememberMe: Boolean,
    showDialog: Boolean,
    navController: NavHostController,
    modifier: Modifier = Modifier
        .offset(y = (-120).dp)
        .padding(horizontal = 25.dp),
) {
    var mensajeConfirmacion by remember { mutableStateOf("") }
    val context: Context = LocalContext.current
    val uiColor = if (isSystemInDarkTheme()) Color.White else Black
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    val api = RetrofitClient.webService
    val factory = UserViewModel.UserViewModelFactory(api, context)
    val viewModel: UserViewModel = viewModel(factory = factory)
    var showDialog by remember { mutableStateOf(false) }

    androidx.compose.material3.Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
        ,

        onClick = {

            if (nombre.isNotEmpty() && apellidos.isNotEmpty() && password.isNotEmpty() &&
                telefono.isNotEmpty() && email.isNotEmpty() && email.contains('@')
            ) {
                val user = User(idUsuario.toString(), nombre, apellidos, email, password, telefono, rol)
                viewModel.signUp(user.email, user.password, user.nombre, user.apellidos, user.telefono, user.rol, user.idUsuario)
                showDialog = true
            } else {
                mensajeConfirmacion =
                    "Por favor, completa todos los campos" // Mensaje de error si falta algún campo
            }
        }
        ,
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

    androidx.compose.material3.OutlinedTextField(
        modifier = modifier,
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

    androidx.compose.material3.OutlinedTextField(
        modifier = modifier,
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

    androidx.compose.material3.OutlinedTextField(
        modifier = modifier,
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

    androidx.compose.material3.OutlinedTextField(
        modifier = modifier,
        value = email,
        onValueChange = { email = it },
        singleLine = true,
        label = {
            Text(
                text = "Correo",
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



    androidx.compose.material3.OutlinedTextField(
        modifier = modifier,
        value = password,
        onValueChange = { password = it },
        singleLine = true,
        label = {
            Text(
                text = "Contraseña",
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





}




@Composable
fun TopSectionRegistro() {
    val uiColor = if (isSystemInDarkTheme()) Color.White else Black

    Box(
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.5f)
                .padding(bottom = 160.dp),

            painter = painterResource(id = R.drawable.shape),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )


        Row(
            modifier = Modifier.padding(top = 30.dp),
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
                .padding(bottom = 140.dp)
                .align(alignment = Alignment.BottomCenter),
            text = stringResource(id = R.string.registro),
            style = MaterialTheme.typography.headlineLarge,
            color = uiColor
        )
    }
}
