package com.example.tfg.Screens.PantallasClientes.Reservas


import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Reservar(navController: NavHostController) {
    var reservas by remember { mutableStateOf(listOf<String>()) }
    val context = LocalContext.current

    Column {
        Button(onClick = {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(context, { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                reservas = reservas + selectedDate
            }, year, month, day)

            datePickerDialog.show()
        }) {
            Text(text = "Reservar Día")
        }

        LazyColumn {
            items(reservas) { reserva ->
                Text(text = reserva)
            }
        }
    }
}

