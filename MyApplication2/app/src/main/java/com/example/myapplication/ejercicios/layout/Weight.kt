package com.example.myapplication.ejercicios.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme



@Composable

fun card(){
    OutlinedCard(onClick = { /*TODO*/ }) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)

    ) {
        Row(
            Modifier.fillMaxWidth()
        ){
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Color.Red)
                    .weight(0.4F)
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Color.White)
                    .weight(0.6F)
                    .padding(16.dp),
                     verticalArrangement = Arrangement.SpaceBetween//agrega espacio entre los componentes de la columna
            ){
                Text(
                    text = "Arroz",
                    fontSize = 30.sp
                )
                Text(
                    text = "Tres cultivos disponibles",
                    fontSize = 24.sp
                    )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center

                ){
                    Button(
                        onClick = { /*TODO*/ }

                    ) {
                        Text(text = "Explorar")
                    }
                }

            }
        }



    }
    }
}

@Preview(showBackground = true)
@Composable
private fun Previewcard() {
    MyApplicationTheme {

        card()
    }
}
