package com.example.little_lemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun Home(navController : NavHostController) {
    Row (modifier=Modifier.fillMaxWidth().padding(top=20.dp),
            horizontalArrangement = Arrangement.spacedBy(
                10.dp)
        ) {
        Image(
            painter = painterResource(id = R.drawable.littlelemonimgtxt),
            contentScale = ContentScale.Fit,
            contentDescription = "logotipo little lemon",
            modifier = Modifier.weight(0.7f)
                .height(80.dp)
                .clip(RoundedCornerShape(12.dp))
        )
        IconButton( onClick ={
                navController.navigate(Profile.route)
            },
            modifier = Modifier.weight(0.3f)
                .height(80.dp)
                .clip(RoundedCornerShape(12.dp)))
        {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentScale = ContentScale.FillHeight,
                contentDescription = "Icono perfil"
            )
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    val navFake = rememberNavController()
    Home(navFake)
}