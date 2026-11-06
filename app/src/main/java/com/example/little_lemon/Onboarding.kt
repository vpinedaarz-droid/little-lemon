package com.example.little_lemon

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.style.Style
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.little_lemon.ui.theme.LittleLemonColor

import com.example.little_lemon.ui.theme.Typography

@Composable
    fun  Onboarding( navController : NavHostController) {
        var nombre by remember { mutableStateOf("") }
        var apellido by remember {mutableStateOf("")}
        var correo by remember {mutableStateOf("")}
        var showErrorDialog by remember { mutableStateOf(false)}

        val context = LocalContext.current
        val sharedPreferences = remember {
            context.getSharedPreferences("LittleLemonPrefs",
                                        Context.MODE_PRIVATE)
        }

        if (showErrorDialog) {
            AlertDialog(
                onDismissRequest = { showErrorDialog = false },
                title = { Text("Error") },
                text = { Text("Information is not complete") },
                confirmButton = {
                    TextButton(onClick = { showErrorDialog = false }) {
                        Text("OK")
                        }
                }
            )
        }
        Column(

        ) {
                Image(
                    painter = painterResource(id = R.drawable.littlelemonimgtxt),
                    contentScale = ContentScale.Fit,
                    contentDescription = "logotipo little lemon",
                    modifier = Modifier.
                    padding(horizontal = 35.dp, vertical = 20.dp)
                )

            Box(modifier = Modifier.background(color = LittleLemonColor.green)
                .height(140.dp).padding(start = 10.dp, end = 10.dp)) {
                Text(
                    text = "Let's get to know you...",
                    fontSize = 25.sp, fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,

                    color = Color.White,
                    style = Typography.headlineLarge,
                    modifier = Modifier.padding( start = 20.dp, end = 20.dp).
                        fillMaxWidth()
                        .align(Alignment.Center)
                    )
            }
            Column(modifier = Modifier.background(color = Color.White)
                .fillMaxWidth()) {
                Text(
                    text = "Personal Information",
                    fontSize = 20.sp, fontWeight = FontWeight.Bold,
                    style = Typography.headlineMedium,
                    modifier = Modifier.padding(top = 40.dp, start = 25.dp,
                                                bottom = 40.dp)
                )
                TextField(
                    textStyle = Typography.bodyMedium,
                    value = nombre,
                    onValueChange = { newNombre -> nombre = newNombre },
                    label = { Text("Name") },
                    singleLine = true,
                    placeholder = { Text("Write your name") },
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,
                        unfocusedTextColor = Color.Gray,
                    )
                )
                TextField(
                    textStyle = Typography.bodyMedium,
                    value = apellido,
                    onValueChange = { newApellido -> apellido = newApellido},
                    label = {Text("Last Name")},
                    singleLine = true,
                    placeholder = {Text("Write your last name")},
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,
                        unfocusedTextColor = Color.Gray,),
                    modifier = Modifier.padding(top = 60.dp)
                )
                TextField(
                    textStyle = Typography.bodyMedium,
                    value = correo,
                    onValueChange = {newCorreo -> correo = newCorreo},
                    label = {Text ("Email")},
                    singleLine = true,
                    placeholder = {Text("Write your email")},
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,
                        unfocusedTextColor = Color.Gray,),
                    modifier = Modifier.padding(top = 60.dp)
                )
            }
            Button(
                onClick = {
                    if (nombre.isBlank() ||
                        apellido.isBlank() ||
                        correo.isBlank()) {
                        showErrorDialog = true
                    } else {
                        showErrorDialog = false
                        sharedPreferences.edit()
                            .putString("nombre_usuario", nombre)
                            .putString("apellido_usuario", apellido)
                            .putString("correo_usuario", correo)
                            .apply()
                        navController.navigate(Home.route)

                    }
                },
                modifier = Modifier.padding(top = 100.dp)
                    .align(Alignment.CenterHorizontally)
                    .width(300.dp)
                    .height(50.dp),
                shape =RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = LittleLemonColor.yellow
                )
            ) {
                Text(text= "Register",
                    color = LittleLemonColor.charcoal,
                    fontSize = 20.sp,
                    )
            }
        }
    }

    @Preview
    @Composable
    fun OnboardingPreview( ) {
        val navController = rememberNavController()
        Onboarding(navController)
    }


