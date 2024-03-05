package com.example.tfg.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button

import androidx.compose.material3.Text
import com.example.tfg.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

import androidx.compose.foundation.layout.height
import androidx.compose.material3.ButtonDefaults

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import com.example.tfg.navigation.AppScreens

@Composable
fun MenuPrimero(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.logo_cerveceria_montemayor),
            contentDescription = "TÍTULO DE LA EMPRESA",
            modifier = Modifier
                .padding(22.dp)
                .height(500.dp)
                .width(300.dp)
        )

        // Botón 1: ACCEDER TRABAJADORES
        Button(
            onClick = { navController.navigate(AppScreens.MenuBotones.ruta) },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(vertical = 15.dp)
                .weight(1f),  // Ajusta el tamaño del botón en relación con otros elementos
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
        ) {
            Text(
                text = "ACCEDER TRABAJADORES",
                fontSize = 25.sp,
            )
        }


        /*ESTE BOTÓN TIENE QUE LLEVAR A LOS CLIENTES AL LECTOR DE CÓDIGOS QR*/
        /*HACER ALGO DE CONTACTLESS*/
        // Botón 2: ACCEDER CLIENTES
        Button(
            onClick = { navController.navigate(AppScreens.MenuBotones.ruta) },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(vertical = 15.dp)
                .weight(1f),  // Ajusta el tamaño del botón en relación con otros elementos
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
        ) {
            Text(
                text = "ACCEDER CLIENTES",
                fontSize = 25.sp,
            )
        }
    }
}
