package com.example.tfg.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.tfg.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Mesa(navController: NavHostController) {
    val scaffoldState = rememberScrollState()
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            // ... (unchanged)
        },
        bottomBar = {
            // ... (unchanged)
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(state = scaffoldState)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                // ... (unchanged)

                // Card for MESA 1
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(390.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.mesa),
                            contentDescription = "MESA 1",
                            modifier = Modifier
                                .padding(22.dp)
                                .height(500.dp)
                                .width(300.dp)
                        )
                    }
                }

                // Card for MESA 2
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(390.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.mesa),
                            contentDescription = "MESA 2",
                            modifier = Modifier
                                .padding(22.dp)
                                .height(500.dp)
                                .width(300.dp)
                        )
                    }
                }


                // Card for MESA 3
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(390.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.mesa),
                            contentDescription = "MESA 2",
                            modifier = Modifier
                                .padding(22.dp)
                                .height(500.dp)
                                .width(300.dp)
                        )
                    }
                }
                // FIN Card for MESA 3



                // Card for MESA 4
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(390.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.mesa),
                            contentDescription = "MESA 2",
                            modifier = Modifier
                                .padding(22.dp)
                                .height(500.dp)
                                .width(300.dp)
                        )
                    }
                }
                // FIN Card for MESA 4


                // Card for MESA 5
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(390.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.mesa),
                            contentDescription = "MESA 2",
                            modifier = Modifier
                                .padding(22.dp)
                                .height(500.dp)
                                .width(300.dp)
                        )
                    }
                }
                // FIN Card for MESA 6





            }
        }
    }
}
