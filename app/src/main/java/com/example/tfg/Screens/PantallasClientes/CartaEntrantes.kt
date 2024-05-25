package com.example.tfg.Screens.PantallasClientes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tfg.R
import com.example.tfg.Retrofit.DataClases.Producto

@Composable
fun CartaEntrantes(navController: NavHostController) {

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            Text(
                text = "CARTA",
                style = TextStyle(
                    color = Color.Blue,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Black
                )
            )
        }

        items(listaProductos) {
            ProductoDiseño(producto = it)
        }

        itemsIndexed(listaProductos) { posicion, producto ->
            if (posicion % 5 == 0 && posicion != 0) {
                LazyRow {
                    items(listaImagenes) {
                        PublicidadDiseño(publicidad = it)
                    }
                }
            } else {
                ProductoDiseño(producto = producto)
            }
        }
    }
}

data class Publicidad(val titulo: String)

private val listaProductos = listOf(
    Producto(nombre = "Manzana", precio = 18.99, categoria = "", descripcion = "", idProducto = 1, imagen = "" ),
    Producto(nombre = "Manzana", precio = 18.99, categoria = "", descripcion = "", idProducto = 1, imagen = "" ),
    Producto(nombre = "Manzana", precio = 18.99, categoria = "", descripcion = "", idProducto = 1, imagen = "" ),
    Producto(nombre = "Manzana", precio = 18.99, categoria = "", descripcion = "", idProducto = 1, imagen = "" ),
    Producto(nombre = "Manzana", precio = 18.99, categoria = "", descripcion = "", idProducto = 1, imagen = "" ),
    Producto(nombre = "Manzana", precio = 18.99, categoria = "", descripcion = "", idProducto = 1, imagen = "" ),
    Producto(nombre = "Manzana", precio = 18.99, categoria = "", descripcion = "", idProducto = 1, imagen = "" ),
    Producto(nombre = "Manzana", precio = 18.99, categoria = "", descripcion = "", idProducto = 1, imagen = "" ),
    Producto(nombre = "Manzana", precio = 18.99, categoria = "", descripcion = "", idProducto = 1, imagen = "" ),
)

private val listaImagenes = listOf(
    R.drawable.gazpacho_andaluz,
    R.drawable.paella,
    R.drawable.cocido_madrileno,
    R.drawable.pulpo_a_la_gallega,
    R.drawable.receta_croquetas_jamon,
    R.drawable.tortilla_patatas,
    //.drawable.imagen3, // Sustituye "imagen3" con el nombre de tu recurso de imagen

)

@Composable
fun ProductoDiseño(producto: Producto) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = producto.nombre,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
        )

        Text(
            text = "${producto.precio}.00 €",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle.Italic
            )
        )
    }
}

@Composable
fun PublicidadDiseño(publicidad: Int) {
    Box(
        modifier = Modifier

            .size(140.dp)
            .clip(RoundedCornerShape(12))
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = publicidad),
            contentDescription = null,
            modifier = Modifier
                .size(140.dp) // Tamaño ajustado para dejar espacio para el padding

        )
    }
}
