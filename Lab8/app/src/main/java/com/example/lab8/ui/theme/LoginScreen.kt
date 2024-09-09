package com.example.lab8.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lab8.R

@Composable
fun LoginScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(R.drawable.rick_morty_logo), contentDescription = "Logo")
        Button(onClick = {
            navController.navigate("characters") {
                popUpTo("login") { inclusive = true }
            }
        }) {
            Text("Empezar")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Juan Francisco Martínez 23617")
    }
}
