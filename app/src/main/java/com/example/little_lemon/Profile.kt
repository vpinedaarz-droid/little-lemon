package com.example.little_lemon

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.little_lemon.ui.theme.LittleLemonColor
import com.example.little_lemon.ui.theme.Typography

@Composable
fun Profile( navController: NavHostController) {

    val context = LocalContext.current
    val sharedPreferences = remember {
        context.getSharedPreferences(
            "LittleLemonPrefs",
            Context.MODE_PRIVATE
        )
    }

    val nombre = sharedPreferences.getString("nombre_usuario", "")
    val apellido = sharedPreferences.getString("apellido_usuario", "")
    val correo = sharedPreferences.getString("correo_usuario", "")
    Column() {
        Image(
            painter = painterResource(id = R.drawable.littlelemonimgtxt),
            contentScale = ContentScale.Fit,
            contentDescription = "logotipo little lemon",
            modifier = Modifier.
            padding(horizontal = 35.dp, vertical = 20.dp)
        )
        Column(modifier = Modifier.background(color = Color.White)
            .fillMaxWidth()) {
            Text(
                text = "Personal Information",
                fontSize = 20.sp, fontWeight = FontWeight.Bold,
                style = Typography.headlineMedium,
                modifier = Modifier.padding(
                    top = 40.dp, start = 25.dp,
                    bottom = 40.dp
                )
            )
            Text(
                style = Typography.bodyMedium,
                text = "First name",
                modifier = Modifier.padding(
                    top = 40.dp, start = 25.dp,
                    bottom = 5.dp)
            )
            Text(
                style = Typography.bodyMedium,
                text = nombre.toString(),
                modifier = Modifier
                    .padding(start = 25.dp)
                    .width(250.dp)
                    .border(width = 1.dp, color = Color.Gray)
            )
            Text(
                style = Typography.bodyMedium,
                text = "Last name",
                modifier = Modifier.padding(
                    top = 40.dp, start = 25.dp,
                    bottom = 5.dp)
            )
            Text(
                style = Typography.bodyMedium,
                text = apellido.toString(),
                modifier = Modifier
                    .padding(start = 25.dp)
                    .width(250.dp)
                    .border(width = 1.dp, color = Color.Gray)
            )
            Text(
                style = Typography.bodyMedium,
                text = "Email",
                modifier = Modifier.padding(
                    top = 40.dp, start = 25.dp,
                    bottom = 5.dp)
            )
            Text(
                style = Typography.bodyMedium,
                text = correo.toString(),
                modifier = Modifier
                    .padding(start = 25.dp)
                    .width(250.dp)
                    .border(width = 1.dp, color = Color.Gray)
            )
            Button(
                onClick = {
                    sharedPreferences.edit()
                        .clear()
                        .apply()
                    navController.navigate(Onboarding.route)
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
                Text(text= "Log out",
                    color = LittleLemonColor.charcoal,
                    fontSize = 20.sp,
                )
            }
        }
    }

}

@Preview
@Composable
fun ProfilePreview() {
    val navConFake = rememberNavController()
    Profile(navConFake)
}