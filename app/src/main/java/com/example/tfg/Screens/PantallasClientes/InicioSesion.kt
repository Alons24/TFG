
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.tfg.R
import com.example.tfg.Retrofit.SessionManager
import com.example.tfg.Retrofit.ViewModels.UserViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioSesion(navController: NavHostController) {
    val userViewModel: UserViewModel = viewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "INICIA SESIÓN", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(20.dp))
        Text(text ="Bienvenido de nuevo", fontSize = 20.sp, color = Color.Gray)

        var nombre by rememberSaveable { mutableStateOf("") }

        OutlinedTextField(
            value = nombre,
            onValueChange = { if (it.length <= 10) nombre = it },
            singleLine = true,
            label = {
                Text("Introduzca su nombre")
            }
        )

        var contrasena by rememberSaveable { mutableStateOf("") }
        OutlinedTextField(
            value = contrasena,
            onValueChange = { if (it.length <= 12) contrasena = it },
            singleLine = true,
            label = { Text("Introduzca su contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )


        RedesSocialesButtons()
        Divider()
        EmailLoginSection()


        Spacer(modifier = Modifier.height(30.dp))
        var correo by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }
        var rememberMe by remember { mutableStateOf(false) }
        val showDialog = remember { mutableStateOf(false) }

        RememberMeCheckbox(rememberMe) {
            rememberMe = it
        }

        LoginButton(correo, password, rememberMe, showDialog, navController, userViewModel, SessionManager)

        if (showDialog.value) {
            ShowErrorDialog {
                showDialog.value = false
            }
        }

        Spacer(modifier = Modifier.size(10.dp))
        ClickableLogin("¿No tienes cuenta?", "Regístrate") {
            navController.navigate("pantallaLogin")
        }
    }
}

@Composable
private fun RedesSocialesButtons() {
    Row {
        SocialButton(R.drawable.google_banner) {
            // Acción al hacer clic en el botón de Google
        }
        Spacer(modifier = Modifier.width(20.dp))
        SocialButton(R.drawable.banner_facebook) {
            // Acción al hacer clic en el botón de Facebook
        }

    }
}




@Composable
private fun SocialButton(iconResId: Int, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(Color.White)
    ) {
        Image(
            painter = painterResource(iconResId),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
    }
}

/*NO PUEDO PERMITIR QUE METEN LOS DATOS QUE QUIERN TENGO QUE REGULARLO*/
@Composable
private fun EmailLoginSection() {
    // Aquí se coloca la sección de inicio de sesión con email
}

@Composable
private fun RememberMeCheckbox(
    rememberMe: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.toggleable(
            value = rememberMe,
            onValueChange = onCheckedChange
        ),
    ) {
        Checkbox(
            checked = rememberMe,
            onCheckedChange = null,
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Gray,
            )
        )
        Text(
            text = "Recuérdame",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}



@Composable
private fun LoginButton(
    correo: String,
    password: String,
    rememberMe: Boolean,
    showDialog: MutableState<Boolean>,
    navController: NavHostController,
    userViewModel: UserViewModel,
    sessionManager: SessionManager
) {
    val correosPermitidos = listOf("gblancocastro@gmail.com", "jramosgarcia@gmail.com", "lgallardo@gmail.com")
    Button(
        onClick = {
            val loginSuccessful = userViewModel.signIn(correo, password)
            if (loginSuccessful) {
                // Si las credenciales son las específicas, navega a la ruta deseada
                if (correo in correosPermitidos) {
                    navController.navigate("MenuTrabajadores")
                } else {
                    // Si las credenciales no son las específicas, navega a la ruta predeterminada
                    navController.navigate("MenuClientes")
                }
            } else {
                showDialog.value = true
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(66.dp, 10.dp, 66.dp, 0.dp),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(Color(59, 64, 72, 255)),
    ) {
        Text(
            text = "LOG IN",
            fontSize = 20.sp,
            color = Color.White
        )
    }
}

@Composable
private fun ShowErrorDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Credenciales incorrectas", color=Color.Black)
        },
        text = {
            Text(text = "Por favor, verifica los campos introducidos", color=Color.Black)
        },
        confirmButton = {
            Button(
                colors = ButtonDefaults.buttonColors(Color(59, 64, 72, 255)),
                modifier = Modifier.fillMaxWidth(),
                onClick = onDismiss
            ) {
                Text(text = "Aceptar")
            }
        }
    )
}

@Composable
private fun ClickableLogin(text1: String, text2: String, onClick: () -> Unit) {
    Row{
        Text(
            text = text1,
            fontSize = 20.sp,
            color = Color.Gray,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = text2,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(4, 104, 249, 255),
            modifier = Modifier.clickable { onClick.invoke() }
        )
    }
}