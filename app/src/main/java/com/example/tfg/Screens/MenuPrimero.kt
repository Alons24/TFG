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

import androidx.compose.runtime.Composable






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


        CustomMenuButton("ACCEDER") {
            navController.navigate("MenuBotones")
        }
    }
}




@Composable
fun CustomMenuButton(text: String, onClickAction: () -> Unit) {
    Button(
        onClick = onClickAction,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Text(
            text = text,
            fontSize = 40.sp,
        )
    }
}

