package com.example.lab5

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.lab5.ui.theme.Lab5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab5Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Lab5(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Lab5(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFE3F2FD))
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,// no logre usar los otros iconos y me dijeron que podia usar estos
                    contentDescription = "Actualización disponible",
                    tint = Color(0xFF3F51B5)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Actualización disponible",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF3F51B5)
            )
            TextButton(onClick = {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=com.ChillyRoom.DungeonShooter")
                )
                context.startActivity(intent)
            }) {
                Text(
                    text = "Descargar",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF3F51B5),
                    modifier = Modifier.wrapContentWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = "Lunes", fontSize = 32.sp, fontWeight = FontWeight.Bold)
                Text(text = "19 de Agosto", fontSize = 20.sp, color = Color.Gray)
            }
            OutlinedButton(onClick = { /*Nada de momento*/ }, shape = CutCornerShape(0)) {
                Text(text = "Terminar jornada")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        Card(
            elevation = CardDefaults.cardElevation(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Text(text = "Naru", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Text(text = "Plaza Futeca Z10 \nC.C. Miraflores\nC.C. Vhista Z15", fontSize = 15.sp)
                    Text(text = "12:00PM - 10:30PM", fontSize = 12.sp)
                }
                IconButton(onClick = {
                    val uri = Uri.parse("geo:14.5822456,-90.5155426?q=Naru+Japanese+Cuisine")
                    val mapIntent = Intent(Intent.ACTION_VIEW, uri)
                    mapIntent.setPackage("com.google.android.apps.maps")
                    context.startActivity(mapIntent)
                },
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Dirección")// no logre usar los otros iconos y me dijeron que podia usar estos
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Button(onClick = {
                    Toast.makeText(context, "Juan Francisco Martínez", Toast.LENGTH_SHORT).show()
                },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7043)), shape = CutCornerShape(0), modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Iniciar", color = Color.White)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    Toast.makeText(context, "Comida Japonesa", Toast.LENGTH_LONG).show()
                }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3F51B5)), shape = CutCornerShape(0), modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Detalles")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab5Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            Lab5(modifier = Modifier.padding(it))
        }
    }
}
