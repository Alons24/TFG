package com.example.tfg.Screens.Trabajadores

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tfg.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreens.AnadirTrabajador(navController: NavHostController){
    Button(
        onClick = { navController.navigate(AppScreens.Despensa.ruta) },
        modifier = Modifier
            .fillMaxWidth()
            .width(300.dp)
            .height(100.dp),
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
    ) {
        Text(
            text = "DESPENSA",
            fontSize = 25.sp,
        )
    }
}