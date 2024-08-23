package com.example.lab6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import com.example.lab6.ui.theme.Lab6Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab6Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ContadorScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ContadorScreen(modifier: Modifier = Modifier) {
    var contador by remember { mutableStateOf(0) }
    var totalIncrementos by remember { mutableStateOf(0) }
    var totalDecrementos by remember { mutableStateOf(0) }
    var valorMaximo by remember { mutableStateOf(0) }
    var valorMinimo by remember { mutableStateOf(0) }
    var totalCambios by remember { mutableStateOf(0) }
    var historial by remember { mutableStateOf(listOf<Pair<Int, Boolean>>()) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Juan Francisco Martínez",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

            Contador(
                contador = contador,
                onIncrement = {
                    contador++
                    totalIncrementos++
                    totalCambios++
                    valorMaximo = maxOf(valorMaximo, contador)
                    historial = historial + Pair(contador, true)
                },
                onDecrement = {
                    contador--
                    totalDecrementos++
                    totalCambios++
                    valorMinimo = minOf(valorMinimo, contador)
                    historial = historial + Pair(contador, false)
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Estadisticas(
                totalIncrementos = totalIncrementos,
                totalDecrementos = totalDecrementos,
                valorMaximo = valorMaximo,
                valorMinimo = valorMinimo,
                totalCambios = totalCambios
            )

            Spacer(modifier = Modifier.height(20.dp))

            Historial(historial = historial)

            Spacer(modifier = Modifier.weight(1f))
        }

        BotonReiniciar(
            onReiniciar = {
                contador = 0
                totalIncrementos = 0
                totalDecrementos = 0
                valorMaximo = 0
                valorMinimo = 0
                totalCambios = 0
                historial = emptyList()
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun Contador(contador: Int, onIncrement: () -> Unit, onDecrement: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onDecrement,
            modifier = Modifier
                .size(50.dp)
                .background(Color.Blue, CircleShape)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.remove),
                contentDescription = "Decrementar",
                tint = Color.White
            )
        }
        Text(
            text = contador.toString(),
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        IconButton(
            onClick = onIncrement,
            modifier = Modifier
                .size(50.dp)
                .background(Color.Blue, CircleShape)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Incrementar",
                tint = Color.White
            )
        }
    }
}

@Composable
fun Estadisticas(
    totalIncrementos: Int,
    totalDecrementos: Int,
    valorMaximo: Int,
    valorMinimo: Int,
    totalCambios: Int
) {
    Column(
        horizontalAlignment = Alignment.Start
    ) {
        EstadisticaItem(label = "Total incrementos:", value = totalIncrementos)
        EstadisticaItem(label = "Total decrementos:", value = totalDecrementos)
        EstadisticaItem(label = "Valor máximo:", value = valorMaximo)
        EstadisticaItem(label = "Valor mínimo:", value = valorMinimo)
        EstadisticaItem(label = "Total cambios:", value = totalCambios)
        Text(
            text = "Historial:",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        )
    }
}

@Composable
fun EstadisticaItem(label: String, value: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            text = value.toString(),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun Historial(historial: List<Pair<Int, Boolean>>) {
    val filasVisibles = 5
    val alturaFila = 50.dp  // Ajusta esto según el tamaño de cada fila

    LazyVerticalGrid(
        columns = GridCells.Fixed(5),
        modifier = Modifier
            .fillMaxWidth()
            .height(alturaFila * filasVisibles),  // Limita la altura para mostrar solo 5 filas
        contentPadding = PaddingValues(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(historial.size) { index ->
            val (valor, esIncremento) = historial[index]
            Box(
                modifier = Modifier
                    .background(
                        color = if (esIncremento) Color.Green else Color.Red,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(8.dp)
                    .aspectRatio(1f)
            ) {
                Text(
                    text = valor.toString(),
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun BotonReiniciar(onReiniciar: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onReiniciar,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Reiniciar", fontSize = 20.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewContadorScreen() {
    Lab6Theme {
        ContadorScreen()
    }
}
